package com.example.wishlist.service;

import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    private WishListRepository wishListRepository;

    @Autowired
    public WishListService(WishListRepository wishListRepository){
        this.wishListRepository = wishListRepository;
    }

    public List<Wish> getAllWishes(){
        return wishListRepository.getAllWishes();
    }
}
