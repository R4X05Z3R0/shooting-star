package com.example.wishlist.controller;

import com.example.wishlist.model.WishList;
import com.example.wishlist.service.WishListService;
import com.example.wishlist.service.WishService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishListController.class)
class WishListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishListService wishListService;

    @MockitoBean
    private WishService wishService;

    @Test
    void createWishlist_redirectsToWishlists_whenUserIsLoggedIn() throws Exception {
        when(wishListService.createWishlist(eq(1), eq("Birthday")))
                .thenReturn(new WishList());

        mockMvc.perform(post("/wishlists/create")
                        .param("title", "Birthday")
                        .sessionAttr("userId", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlists/"));

        verify(wishListService).createWishlist(1, "Birthday");
    }

    @Test
    void createWishlist_redirectsToLogin_whenNoUserInSession() throws Exception {
        mockMvc.perform(post("/wishlists/create")
                        .param("title", "Birthday"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/shootingstar/"));

        verify(wishListService, never()).createWishlist(anyInt(), any());
    }
}