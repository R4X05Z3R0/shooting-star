package com.example.wishlist.mapper;

import com.example.wishlist.model.Wish;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishRowMapper implements RowMapper<Wish> {

    @Override
    public Wish mapRow(ResultSet rs, int rowNum) throws SQLException {

        Wish wish = new Wish();

        wish.setWishId(rs.getInt("wish_id"));
        wish.setWishTitle(rs.getString("wish_title"));
        Double price = rs.getObject("price", Double.class);
        wish.setPrice(price != null ? price : 0.0);
        wish.setDescription(rs.getString("description"));
        wish.setUrl(rs.getString("url"));
        wish.setImageUrl(rs.getString("image_url"));
        wish.setWishlistId(rs.getInt("wishlist_id"));

        return wish;
    }
}