package com.example.wishlist.repository;

import com.example.wishlist.mapper.WishRowMapper;
import com.example.wishlist.model.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class WishRepository {

    private final JdbcTemplate jdbcTemplate;
    private final WishRowMapper wishRowMapper;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.wishRowMapper = new WishRowMapper();
    }

    public List<Wish> findByWishlistId(int wishlistId) {
        String sql = """
                SELECT wish_id, wish_title, price, description, url, image_url, wishlist_id
                FROM wish
                WHERE wishlist_id = ?
                ORDER BY wish_id
                """;
        return jdbcTemplate.query(sql, wishRowMapper, wishlistId);
    }

    public Wish findByIdAndUser(int wishId, int userId) {
        String sql = """
        SELECT w.wish_id, w.wish_title, w.price, w.description, w.url, w.image_url, w.wishlist_id
        FROM wish w
        JOIN wishlist wl ON wl.wishlist_id = w.wishlist_id
        WHERE w.wish_id = ? AND wl.user_id = ?
        """;

        return jdbcTemplate.queryForObject(sql, wishRowMapper, wishId, userId);
    }

    public Wish save(Wish wish) {
        String sql = """
                INSERT INTO wish (wish_title, price, description, url, image_url, wishlist_id)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, wish.getWishTitle());
            ps.setDouble(2, wish.getPrice());
            ps.setString(3, wish.getDescription());
            ps.setString(4, wish.getUrl());
            ps.setString(5, wish.getImageUrl());
            ps.setInt(6, wish.getWishlistId());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            wish.setWishId(key.intValue());
        }
        return wish;
    }

    public void update(Wish wish) {
        String sql = """
                UPDATE wish
                SET wish_title = ?, price = ?, description = ?, url = ?, image_url = ?
                WHERE wish_id = ?
                """;
        jdbcTemplate.update(sql,
                wish.getWishTitle(),
                wish.getPrice(),
                wish.getDescription(),
                wish.getUrl(),
                wish.getImageUrl(),
                wish.getWishId());
    }

    public void deleteById(int wishId) {
        String sql = "DELETE FROM wish WHERE wish_id = ?";
        jdbcTemplate.update(sql, wishId);
    }
}