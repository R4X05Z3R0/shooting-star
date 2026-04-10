package com.example.wishlist.repository;

import com.example.wishlist.model.Wish;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository {
    private JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wish>getAllWishes(){
        String sql = "SELECT * FROM wish";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Wish.class));
    }
}
