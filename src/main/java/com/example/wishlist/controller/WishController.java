package com.example.wishlist.controller;

import com.example.wishlist.model.Wish;
import com.example.wishlist.service.WishService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
@RequestMapping("/wishlists/{wishlistId}/wishes")
public class WishController {

    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping
    public String createWish(@PathVariable int wishlistId,
                             @RequestParam String wishTitle,
                             @RequestParam(required = false) Double price,
                             @RequestParam(required = false) String description,
                             @RequestParam(required = false) String url,
                             @RequestParam(required = false) String imageUrl,
                             HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        Wish wish = new Wish();
        wish.setWishTitle(wishTitle);
        wish.setPrice(price);
        wish.setDescription(description);
        wish.setUrl(url);
        wish.setImageUrl(imageUrl);
        wish.setWishlistId(wishlistId);

        wishService.createWish(wish, userId);
        return "redirect:/wishlists/" + wishlistId;
    }

    @GetMapping("/{wishId}/edit")
    public String showEditForm(@PathVariable int wishlistId,
                               @PathVariable int wishId,
                               HttpSession session,
                               Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        Optional<Wish> wish = wishService.getWishForUser(wishId, userId);
        if (wish.isEmpty() || wish.get().getWishlistId() != wishlistId) {
            return "redirect:/wishlists/" + wishlistId;
        }

        model.addAttribute("wish", wish.get());
        model.addAttribute("wishlistId", wishlistId);
        return "wish-edit";
    }

    @PostMapping("/{wishId}/update")
    public String updateWish(@PathVariable int wishlistId,
                             @PathVariable int wishId,
                             @RequestParam String wishTitle,
                             @RequestParam(required = false) double price,
                             @RequestParam(required = false) String description,
                             @RequestParam(required = false) String url,
                             @RequestParam(required = false) String imageUrl,
                             HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        Wish wish = new Wish();
        wish.setWishId(wishId);
        wish.setWishTitle(wishTitle);
        wish.setPrice(price);
        wish.setDescription(description);
        wish.setUrl(url);
        wish.setImageUrl(imageUrl);

        wishService.updateWish(wish, userId);
        return "redirect:/wishlists/" + wishlistId;
    }

    @PostMapping("/{wishId}/delete")
    public String deleteWish(@PathVariable int wishlistId,
                             @PathVariable int wishId,
                             HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        wishService.deleteWish(wishId, userId);
        return "redirect:/wishlists/" + wishlistId;
    }
}