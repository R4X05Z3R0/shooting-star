package com.example.wishlist.repository;

import com.example.wishlist.model.Wish;
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
public class WishRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wish> findByWishlistId(int wishlistId) {
        String sql = """
                SELECT wish_id, wish_title, price, description, url, image_url, wishlist_id
                FROM wish
                WHERE wishlist_id = ?
                ORDER BY wish_id
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Wish.class), wishlistId);
    }

    public Optional<Wish> findById(int wishId) {
        String sql = """
                SELECT wish_id, wish_title, price, description, url, image_url, wishlist_id
                FROM wish
                WHERE wish_id = ?
                """;
        List<Wish> results = jdbcTemplate.query(
                sql, new BeanPropertyRowMapper<>(Wish.class), wishId);
        return results.stream().findFirst();
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