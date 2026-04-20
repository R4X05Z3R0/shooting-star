package com.example.wishlist.repository;

import com.example.wishlist.model.Wish;
import com.example.wishlist.model.WishList;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class WishListRepository {

    private JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    // Finder
    public List<WishList> findWishlistByUserId(int userId) {
        String sql = "SELECT wishlist_id, user_id FROM wishlist WHERE user_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WishList.class), userId);
    }

    public Optional<WishList> getWishlistForUser(int wishlistId, int userId) {
        return this.findById(wishlistId)
                .filter(wl -> wl.getUserId() == userId);
    }
    public Optional<WishList> findById(int wishlistId) {
        String sql = "SELECT wishlist_id, title, user_id FROM wishlist WHERE wishlist_id = ?";
        List<WishList> results = jdbcTemplate.query(
                sql, new BeanPropertyRowMapper<>(WishList.class), wishlistId);
        return results.stream().findFirst();
    }
    // gemmer

    public WishList saveWishlist(WishList wishList) {
        String sql = "INSERT INTO wishlist(title, user_id VALUES(?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps =
                    connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, wishList.getTitle());
            ps.setInt(2, wishList.getUserId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            wishList.setWishlistID(keyHolder.getKey().intValue());
        }
        return wishList;

    }
//retter
    public void update(WishList wishList){
    String sql = "UPDATE wishlist SET title = ? Where wishlist_id = ?";
    jdbcTemplate.update(sql, wishList.getTitle(), wishList.getWishlistID());
    }
    public void deleteById(int wishlistId) {
        String  sql = "DELETE FROM wishlist WHERE wishlist_id = ?";
        jdbcTemplate.update(sql, wishlistId);

    }
}

