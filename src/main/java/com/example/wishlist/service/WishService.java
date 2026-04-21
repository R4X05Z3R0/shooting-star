package com.example.wishlist.service;

import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    // GET all wishes in a wishlist
    public List<Wish> getWishes(int wishlistId) {
        return wishRepository.findByWishlistId(wishlistId);
    }

    // GET single wish (ownership enforced in SQL)
    public Wish getWish(int wishId, int userId) {
        return wishRepository.findByIdAndUser(wishId, userId);
    }

    // CREATE
    public Wish createWish(Wish wish) {
        if (wish.getUrl() != null && !wish.getUrl().startsWith("http")) {//HTTP inserter
            wish.setUrl("https://" + wish.getUrl());
        }
        if (wish.getImageUrl() != null && !wish.getImageUrl().startsWith("http")) {//HTTP inserter
            wish.setUrl("https://" + wish.getImageUrl());
        }
        return wishRepository.save(wish);
    }

    // UPDATE (ownership enforced in SQL via find first)
    public boolean updateWish(Wish wish, int userId) {
        Wish existing = wishRepository.findByIdAndUser(wish.getWishId(), userId);

        if (existing == null) {
            return false;
        }

        wish.setWishlistId(existing.getWishlistId());
        wishRepository.update(wish);
        return true;
    }

    // DELETE (same pattern: verify ownership first)
    public boolean deleteWish(int wishId, int userId) {
        Wish existing = wishRepository.findByIdAndUser(wishId, userId);

        if (existing == null) {
            return false;
        }

        wishRepository.deleteById(wishId);
        return true;
    }
}