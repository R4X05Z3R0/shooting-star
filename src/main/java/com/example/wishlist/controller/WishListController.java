package com.example.wishlist.controller;

import com.example.wishlist.model.Wish;
import com.example.wishlist.model.WishList;
import com.example.wishlist.service.WishListService;
import com.example.wishlist.service.WishService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/wishlists")
public class WishListController {

    private final WishListService wishListService;
    private final WishService wishService;

    public WishListController(WishListService wishListService, WishService wishService) {
        this.wishListService = wishListService;
        this.wishService = wishService;
    }

    @GetMapping("/")
    public String listWishlists(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/shootingstar/login";

        List<WishList> wishlists = wishListService.getUserWishlists(userId);
        model.addAttribute("wishlists", wishlists);
        return "dashboard";
    }

    @GetMapping("/new")
    public String showCreateForm(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) return "redirect:/shootingstar/login";
        model.addAttribute("wishlist", new WishList());
        return "wishlist-new";
    }

    @PostMapping("/create")
    public String createWishlist(@RequestParam String title, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/shootingstar/login";

        wishListService.createWishlist(userId, title);
        return "redirect:/wishlists/";
    }

    @GetMapping("/{wishlistId}")
    public String showWishlist(@PathVariable int wishlistId,
                               HttpSession session,
                               Model model) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            //System.out.println("No userId in session");
            return "redirect:/wishlists/";
        }

//        System.out.println("wishlistId = " + wishlistId);
//        System.out.println("userId = " + userId);

        List<Wish> wishes = wishService.getWishes(wishlistId);
        WishList wishList = wishListService.findWishlistByWishlistId(wishlistId);


//        System.out.println("wishes size = " + (wishes != null ? wishes.size() : "null"));
//        System.out.println("wishes = " + wishes);

        model.addAttribute("wishes", wishes);
        model.addAttribute("wishlistId", wishlistId);
        model.addAttribute("title", wishList.getTitle());

        return "view-wishes";
    }

    @GetMapping("/{wishlistId}/edit")
    public String showEditForm(@PathVariable int wishlistId, HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/shootingstar/login";

        Optional<WishList> wishlist = wishListService.getWishlistForUser(wishlistId, userId);
        if (wishlist.isEmpty()) return "redirect:/wishlists/";

        model.addAttribute("wishlist", wishlist.get());
        return "wishlist-edit";
    }

    @PostMapping("/{wishlistId}/update")
    public String updateWishlist(@PathVariable int wishlistId,
                                 @RequestParam String title,
                                 HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/shootingstar/login";

        wishListService.updateWishlist(wishlistId, userId, title);
        return "redirect:/wishlists/";
    }

    @PostMapping("/{wishlistId}/delete")
    public String deleteWishlist(@PathVariable int wishlistId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/shootingstar/login";

        wishListService.deleteWishlist(wishlistId, userId);
        return "redirect:/wishlists/";
    }
}