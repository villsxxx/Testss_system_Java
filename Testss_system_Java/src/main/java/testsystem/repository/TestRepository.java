package testsystem.repository;

import testsystem.model.Test;
import testsystem.util.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestRepository implements BaseRepository<Test> {

    @Override
    public List<Test> findAll(int page, int size) {
        List<Test> tests = new ArrayList<>();
        String sql = "SELECT test_id, title, description, is_active, created_at FROM Tests ORDER BY test_id LIMIT ? OFFSET ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, size);
            stmt.setInt(2, (page - 1) * size);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Test test = new Test();
                test.setId(rs.getLong("test_id"));
                test.setTitle(rs.getString("title"));
                test.setDescription(rs.getString("description"));
                test.setActive(rs.getBoolean("is_active"));
                test.setCreatedAt(rs.getTimestamp("created_at"));
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM Tests";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void insert(Test test) {
        String sql = "INSERT INTO Tests (title, description, is_active, created_at) VALUES (?, ?, ?, NOW())";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, test.getTitle());
            stmt.setString(2, test.getDescription());
            stmt.setBoolean(3, test.getActive());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Test test) {
        String sql = "UPDATE Tests SET title = ?, description = ?, is_active = ? WHERE test_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, test.getTitle());
            stmt.setString(2, test.getDescription());
            stmt.setBoolean(3, test.getActive());
            stmt.setLong(4, test.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM Tests WHERE test_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Test> findById(long id) {
        List<Test> tests = new ArrayList<>();
        String sql = "SELECT test_id, title, description, is_active, created_at FROM Tests WHERE test_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Test test = new Test();
                test.setId(rs.getLong("test_id"));
                test.setTitle(rs.getString("title"));
                test.setDescription(rs.getString("description"));
                test.setActive(rs.getBoolean("is_active"));
                test.setCreatedAt(rs.getTimestamp("created_at"));
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

}