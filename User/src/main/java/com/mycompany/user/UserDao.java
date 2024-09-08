package com.mycompany.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected User extractObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
