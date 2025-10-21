package testsystem.repository;

import testsystem.model.Answer;
import testsystem.util.DatabaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerRepository implements BaseRepository<Answer> {

    @Override
    public List<Answer> findAll(int page, int size) {
        List<Answer> answers = new ArrayList<>();
        String sql = "SELECT answer_id, question_id, answer_text, is_correct, points FROM Answers ORDER BY answer_id LIMIT ? OFFSET ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, size);
            stmt.setInt(2, (page - 1) * size);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Answer a = new Answer();
                a.setId(rs.getLong("answer_id"));
                a.setQuestionId(rs.getLong("question_id"));
                a.setText(rs.getString("answer_text"));
                a.setCorrect(rs.getBoolean("is_correct"));
                a.setPoints(rs.getInt("points"));
                answers.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM Answers";
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
    public void insert(Answer answer) {
        String sql = "INSERT INTO Answers (question_id, answer_text, is_correct, points) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, answer.getQuestionId());
            stmt.setString(2, answer.getText());
            stmt.setBoolean(3, answer.getCorrect());
            stmt.setInt(4, answer.getPoints());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Answer answer) {
        String sql = "UPDATE Answers SET question_id = ?, answer_text = ?, is_correct = ?, points = ? WHERE answer_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, answer.getQuestionId());
            stmt.setString(2, answer.getText());
            stmt.setBoolean(3, answer.getCorrect());
            stmt.setInt(4, answer.getPoints());
            stmt.setLong(5, answer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM Answers WHERE answer_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Answer> findById(long questionId) {
        List<Answer> answers = new ArrayList<>();
        String sql = "SELECT answer_id, question_id, answer_text, is_correct, points FROM Answers WHERE question_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, questionId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Answer a = new Answer();
                a.setId(rs.getLong("answer_id"));
                a.setQuestionId(rs.getLong("question_id"));
                a.setText(rs.getString("answer_text"));
                a.setCorrect(rs.getBoolean("is_correct"));
                a.setPoints(rs.getInt("points"));
                answers.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
}