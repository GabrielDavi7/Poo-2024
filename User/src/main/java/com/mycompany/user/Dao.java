package com.mycompany.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao<E> implements IDao<E> {
    protected Connection connection;

    public Dao() {
        try {
            this.connection = DbConnection.getInstance().getConnection();
        } catch (Exception e) {
            System.err.println("Erro ao obter conexão: " + e.getMessage());
        }
    }

    public abstract String getSaveStatement();
    public abstract String getUpdateStatement();
    public abstract String getFindByIdStatement();
    public abstract String getFindAllStatement();
    public abstract String getDeleteStatement();
    public abstract void composeSaveOrUpdateStatement(PreparedStatement pstmt, E entity) throws SQLException;

    @Override
    public Long save(E entity) {
        try (PreparedStatement pstmt = connection.prepareStatement(getSaveStatement(), Statement.RETURN_GENERATED_KEYS)) {
            composeSaveOrUpdateStatement(pstmt, entity);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao salvar a entidade, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Falha ao salvar a entidade, não foi possível obter o ID gerado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar entidade: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(E entity) {
        try (PreparedStatement pstmt = connection.prepareStatement(getUpdateStatement())) {
            composeSaveOrUpdateStatement(pstmt, entity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar entidade: " + e.getMessage());
        }
    }

    @Override
    public E findById(Long id) {
        try (PreparedStatement pstmt = connection.prepareStatement(getFindByIdStatement())) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractObject(rs);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar entidade por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<E> findAll() {
        List<E> entities = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(getFindAllStatement());

            while (rs.next()) {
                entities.add(extractObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar entidades: " + e.getMessage());
        }
        return entities;
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement pstmt = connection.prepareStatement(getDeleteStatement())) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar entidade: " + e.getMessage());
        }
    }

    protected abstract E extractObject(ResultSet rs) throws SQLException;
}
