package com.mycompany.user;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class UserDao extends Dao<User> {

    @Override
    public String getSaveStatement() {
        return "INSERT INTO user (name, email, password, lastAccess, active) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateStatement() {
        return "UPDATE user SET name = ?, email = ?, password = ?, lastAccess = ?, active = ? WHERE id = ?";
    }

    @Override
    public String getFindByIdStatement() {
        return "SELECT * FROM user WHERE id = ?";
    }

    @Override
    public String getFindAllStatement() {
        return "SELECT * FROM user";
    }

    @Override
    public String getDeleteStatement() {
        return "DELETE FROM user WHERE id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, User entity) throws SQLException {
        pstmt.setString(1, entity.getName());
        pstmt.setString(2, entity.getEmail());
        pstmt.setString(3, entity.getPassword()); 
        pstmt.setBoolean(5, entity.isActive());
        
        if (entity.getId() != null) {
            pstmt.setLong(6, entity.getId());
        }
    }

    @Override
    protected User extractObject(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("senha"));
        user.setLastAccess(rs.getObject("lastAccess", LocalDateTime.class));
        user.setActive(rs.getBoolean("active"));
        return user;
    }
}
