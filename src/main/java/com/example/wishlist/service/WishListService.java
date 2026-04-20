package com.example.wishlist.service;

import com.example.wishlist.model.WishList;
import com.example.wishlist.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public List<WishList> getUserWishlists(int userId) {
        return wishListRepository.findWishlistByUserId(userId);
    }

    public Optional<WishList> getWishlistForUser(int wishlistId, int userId) {
        return wishListRepository.getWishlistForUser(wishlistId,userId);
    }

    public WishList createWishlist(int userId, String title) {
        WishList wishList = new WishList();
        wishList.setTitle(title);
        wishList.setUserId(userId);
        return wishListRepository.saveWishlist(wishList);
    }

    public boolean updateWishlist(int wishlistId, int userId, String newTitle) {
        Optional<WishList> owned = getWishlistForUser(wishlistId, userId);
        if (owned.isEmpty()) return false;
        WishList wishList = owned.get();
        wishList.setTitle(newTitle);
        wishListRepository.update(wishList);
        return true;
    }

    public boolean deleteWishlist(int wishlistId, int userId) {
        if (getWishlistForUser(wishlistId, userId).isEmpty()) return false;
        wishListRepository.deleteById(wishlistId);
        return true;
    }
}