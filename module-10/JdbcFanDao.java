
// Justin Marucci 
// Assignment 10
// 10-1-2025


package com.meco.fans.dao;

import com.meco.fans.model.Fan;
import java.sql.*;
import java.util.Optional;

public class JdbcFanDao implements FanDao {

    private static final String URL  = "jdbc:mysql://localhost:3306/databasedb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "student1";
    private static final String PASS = "pass";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    @Override
    public Optional<Fan> findById(int id) throws Exception {
        String sql = "SELECT ID, firstname, lastname, favoriteteam FROM fans WHERE ID = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Fan f = new Fan(
                        rs.getInt("ID"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("favoriteteam")
                    );
                    return Optional.of(f);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public int update(Fan fan) throws Exception {
        String sql = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fan.getFirstName());
            ps.setString(2, fan.getLastName());
            ps.setString(3, fan.getFavoriteTeam());
            ps.setInt(4, fan.getId());
            return ps.executeUpdate();
        }
    }
}
