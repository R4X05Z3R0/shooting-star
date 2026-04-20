package com.example.wishlist.service;

import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishService {

    private final WishRepository wishRepository;
    private final WishListService wishListService;

    public WishService(WishRepository wishRepository, WishListService wishListService) {
        this.wishRepository = wishRepository;
        this.wishListService = wishListService;
    }

    public List<Wish> getWishesForUserWishlist(int wishlistId, int userId) {
        if (wishListService.getWishlistForUser(wishlistId, userId).isEmpty()) {
            return List.of();
        }
        return wishRepository.findByWishlistId(wishlistId);
    }

    public Optional<Wish> getWishForUser(int wishId, int userId) {
        Optional<Wish> wish = wishRepository.findById(wishId);
        if (wish.isEmpty()) return Optional.empty();
        if (wishListService.getWishlistForUser(wish.get().getWishlistId(), userId).isEmpty()) {
            return Optional.empty();
        }
        return wish;
    }
    public Optional<Wish> createWish(Wish wish, int userId) {
        if (wishListService.getWishlistForUser(wish.getWishlistId(), userId).isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(wishRepository.save(wish));
    }

    public boolean updateWish(Wish wish, int userId) {
        Optional<Wish> existing = getWishForUser(wish.getWishId(), userId);
        if (existing.isEmpty()) return false;

        wish.setWishlistId(existing.get().getWishlistId());
        wishRepository.update(wish);
        return true;
    }

    public boolean deleteWish(int wishId, int userId) {
        if (getWishForUser(wishId, userId).isEmpty()) return false;
        wishRepository.deleteById(wishId);
        return true;
    }
}