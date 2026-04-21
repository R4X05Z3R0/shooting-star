package com.example.wishlist.mapper;

import com.example.wishlist.model.WishList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistRowMapper implements RowMapper<WishList> {
    public WishList mapRow(ResultSet rs, int rowNum) throws SQLException {
        WishList wishList = new WishList();

        wishList.setWishlistId(rs.getInt("wishlist_id"));
        wishList.setTitle(rs.getString("title"));
        wishList.setUserId(rs.getInt("user_id"));

        return wishList;
    }
}
