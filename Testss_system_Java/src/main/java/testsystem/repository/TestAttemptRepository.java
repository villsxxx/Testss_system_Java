package testsystem.repository;

import testsystem.model.TestAttempt;
import testsystem.util.DatabaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestAttemptRepository implements BaseRepository<TestAttempt> {

    @Override
    public List<TestAttempt> findAll(int page, int size) {
        List<TestAttempt> attempts = new ArrayList<>();
        String sql = "SELECT attempt_id, user_id, test_id, score, started_at, finished_at FROM Test_Attempts ORDER BY attempt_id LIMIT ? OFFSET ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, size);
            stmt.setInt(2, (page - 1) * size);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TestAttempt ta = new TestAttempt();
                ta.setId(rs.getLong("attempt_id"));
                ta.setUserId(rs.getLong("user_id"));
                ta.setTestId(rs.getLong("test_id"));
                ta.setScore(rs.getInt("score"));
                ta.setStartedAt(rs.getTimestamp("started_at"));
                ta.setFinishedAt(rs.getTimestamp("finished_at"));
                attempts.add(ta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attempts;
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM Test_Attempts";
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
    public void insert(TestAttempt attempt) {
        String sql = "INSERT INTO Test_Attempts (user_id, test_id, score, started_at, finished_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, attempt.getUserId());
            stmt.setLong(2, attempt.getTestId());
            stmt.setInt(3, attempt.getScore());
            stmt.setTimestamp(4, attempt.getStartedAt());
            stmt.setTimestamp(5, attempt.getFinishedAt());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TestAttempt attempt) {
        String sql = "UPDATE Test_Attempts SET user_id = ?, test_id = ?, score = ?, started_at = ?, finished_at = ? WHERE attempt_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, attempt.getUserId());
            stmt.setLong(2, attempt.getTestId());
            stmt.setInt(3, attempt.getScore());
            stmt.setTimestamp(4, attempt.getStartedAt());
            stmt.setTimestamp(5, attempt.getFinishedAt());
            stmt.setLong(6, attempt.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM Test_Attempts WHERE attempt_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

@Override
    public List<TestAttempt> findById(long userId) {
        List<TestAttempt> attempts = new ArrayList<>();
        String sql = "SELECT attempt_id, user_id, test_id, score, started_at, finished_at FROM Test_Attempts WHERE user_id = ? ";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TestAttempt ta = new TestAttempt();
                ta.setId(rs.getLong("attempt_id"));
                ta.setUserId(rs.getLong("user_id"));
                ta.setTestId(rs.getLong("test_id"));
                ta.setScore(rs.getInt("score"));
                ta.setStartedAt(rs.getTimestamp("started_at"));
                ta.setFinishedAt(rs.getTimestamp("finished_at"));
                attempts.add(ta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attempts;
    }
}