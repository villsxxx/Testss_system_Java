package testsystem.repository;

import testsystem.model.Question;
import testsystem.util.DatabaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository implements BaseRepository<Question> {

    @Override
    public List<Question> findAll(int page, int size) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT question_id, test_id, question_text, question_type FROM Questions ORDER BY question_id LIMIT ? OFFSET ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, size);
            stmt.setInt(2, (page - 1) * size);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getLong("question_id"));
                q.setTestId(rs.getLong("test_id"));
                q.setText(rs.getString("question_text"));
                q.setType(rs.getString("question_type"));
                questions.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM Questions";
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
    public void insert(Question question) {
        String sql = "INSERT INTO Questions (test_id, question_text, question_type) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, question.getTestId());
            stmt.setString(2, question.getText());
            stmt.setString(3, question.getType());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Question question) {
        String sql = "UPDATE Questions SET test_id = ?, question_text = ?, question_type = ? WHERE question_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, question.getTestId());
            stmt.setString(2, question.getText());
            stmt.setString(3, question.getType());
            stmt.setLong(4, question.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM Questions WHERE question_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   @Override
   public List<Question> findById(long testId) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT question_id, test_id, question_text, question_type FROM Questions WHERE test_id = ? ";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, testId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getLong("question_id"));
                q.setTestId(rs.getLong("test_id"));
                q.setText(rs.getString("question_text"));
                q.setType(rs.getString("question_type"));
                questions.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}