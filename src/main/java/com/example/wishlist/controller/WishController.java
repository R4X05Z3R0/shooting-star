package com.example.wishlist.controller;

import com.example.wishlist.model.Wish;
import com.example.wishlist.service.WishService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/wishlists/{wishlistId}/wishes")
public class WishController {

    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    // VIEW create form page
    @GetMapping("/create")
    public String showCreateForm(@PathVariable int wishlistId,
                                 HttpSession session,
                                 Model model) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/shootingstar/login";
        }

        Wish wish = new Wish();
        wish.setWishlistId(wishlistId);

        model.addAttribute("wish", wish);
        return "wish-create";
    }

    // CREATE
    @PostMapping("/create")
    public String createWish(@PathVariable int wishlistId,
                             @ModelAttribute Wish wish,
                             HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/shootingstar/login";

        wish.setWishlistId(wishlistId);

        wishService.createWish(wish);

        return "redirect:/wishlists/" + wishlistId;
    }

    // EDIT FORM
    @GetMapping("/{wishId}/edit")
    public String showEditForm(@PathVariable int wishId,
                               @PathVariable int wishlistId,
                               HttpSession session,
                               Model model) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/shootingstar/login";
        }

        Wish wish = wishService.getWish(wishId, userId);

        if (wish == null) {
            return "redirect:/wishlists/" + wishlistId;
        }

        model.addAttribute("wish", wish);
        return "wish-edit";
    }

    // UPDATE
    @PostMapping("/{wishId}/update")
    public String updateWish(@PathVariable int wishlistId,
                             @PathVariable int wishId,
                             @ModelAttribute Wish wish,
                             HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null)
        {
            return "redirect:/shootingstar/login";
        }

        wish.setWishId(wishId);

        wishService.updateWish(wish, userId);

        return "redirect:/wishlists/" + wishlistId;
    }

    // DELETE
    @PostMapping("/{wishId}/delete")
    public String deleteWish(@PathVariable int wishlistId,
                             @PathVariable int wishId,
                             HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null){
            return "redirect:/shootingstar/login";
        }

        wishService.deleteWish(wishId, userId);

        return "redirect:/wishlists/" + wishlistId;
    }
}