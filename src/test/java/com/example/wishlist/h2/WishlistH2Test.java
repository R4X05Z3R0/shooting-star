package com.example.wishlist.h2;
import com.example.wishlist.model.WishList;
import com.example.wishlist.repository.WishListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class WishlistH2Test {

    @Autowired
    private WishListRepository wishListRepository;

    @Test
    void testFindWishlistByUserId() {

        List<WishList> wishLists = wishListRepository.findWishlistByUserId(1);

        assertThat(wishLists).isNotEmpty();
    }

    @Test
    void testSaveWishlist() {
        WishList wl = new WishList();
        wl.setTitle("Naruto");
        wl.setUserId(1);

        WishList saved = wishListRepository.saveWishlist(wl);

        assertThat(saved.getWishlistId()).isEqualTo(8);
        assertThat(saved.getTitle()).isEqualTo("Naruto");
    }

    @Test
    void testFindById() {

        WishList wl = new WishList();
        wl.setTitle("Sharingan");
        wl.setUserId(1);
        WishList saved = wishListRepository.saveWishlist(wl);

        WishList found = wishListRepository.findWishlistById(saved.getWishlistId());

        assertNotNull(found);
        assertThat(found.getTitle()).isEqualTo("Sharingan");
    }

    @Test
    void testUpdateWishlist() {

        WishList wl = new WishList();
        wl.setTitle("Naruto");
        wl.setUserId(1);
        WishList saved = wishListRepository.saveWishlist(wl);


        saved.setTitle("Sasuke");
        wishListRepository.update(saved);


        WishList updated = wishListRepository.findWishlistById(saved.getWishlistId());
        assertThat(updated.getTitle()).isEqualTo("Sasuke");
    }

    @Test
    void testDeleteById() {
        WishList wl = new WishList();
        wl.setTitle("Deleted");
        wl.setUserId(1);
        WishList saved = wishListRepository.saveWishlist(wl);

        wishListRepository.deleteById(saved.getWishlistId());

        Optional<WishList> deleted = wishListRepository.findById(saved.getWishlistId());
        assertThat(deleted).isEmpty();
    }
}