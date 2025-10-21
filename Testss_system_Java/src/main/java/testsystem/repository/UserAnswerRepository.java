package testsystem.repository;

import testsystem.model.UserAnswer;
import testsystem.util.DatabaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAnswerRepository implements BaseRepository<UserAnswer> {

    @Override
    public List<UserAnswer> findAll(int page, int size) {
        List<UserAnswer> userAnswers = new ArrayList<>();
        String sql = "SELECT user_answer_id, attempt_id, question_id, answer_id, text_answer FROM User_Answers ORDER BY user_answer_id LIMIT ? OFFSET ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, size);
            stmt.setInt(2, (page - 1) * size);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UserAnswer ua = new UserAnswer();
                ua.setId(rs.getLong("user_answer_id"));
                ua.setAttemptId(rs.getLong("attempt_id"));
                ua.setQuestionId(rs.getLong("question_id"));
                ua.setAnswerId(rs.getObject("answer_id") == null ? null : rs.getLong("answer_id"));
                ua.setTextAnswer(rs.getString("text_answer"));
                userAnswers.add(ua);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAnswers;
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM User_Answers";
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
    public void insert(UserAnswer userAnswer) {
        String sql = "INSERT INTO User_Answers (attempt_id, question_id, answer_id, text_answer) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userAnswer.getAttemptId());
            stmt.setLong(2, userAnswer.getQuestionId());
            if (userAnswer.getAnswerId() != null) {
                stmt.setLong(3, userAnswer.getAnswerId());
            } else {
                stmt.setNull(3, java.sql.Types.BIGINT);
            }
            stmt.setString(4, userAnswer.getTextAnswer());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserAnswer userAnswer) {
        String sql = "UPDATE User_Answers SET attempt_id = ?, question_id = ?, answer_id = ?, text_answer = ? WHERE user_answer_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userAnswer.getAttemptId());
            stmt.setLong(2, userAnswer.getQuestionId());
            if (userAnswer.getAnswerId() != null) {
                stmt.setLong(3, userAnswer.getAnswerId());
            } else {
                stmt.setNull(3, java.sql.Types.BIGINT);
            }
            stmt.setString(4, userAnswer.getTextAnswer());
            stmt.setLong(5, userAnswer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM User_Answers WHERE user_answer_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserAnswer> findById(long attemptId) {
        List<UserAnswer> userAnswers = new ArrayList<>();
        String sql = "SELECT user_answer_id, attempt_id, question_id, answer_id, text_answer FROM User_Answers WHERE attempt_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, attemptId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UserAnswer ua = new UserAnswer();
                ua.setId(rs.getLong("user_answer_id"));
                ua.setAttemptId(rs.getLong("attempt_id"));
                ua.setQuestionId(rs.getLong("question_id"));
                ua.setAnswerId(rs.getObject("answer_id") == null ? null : rs.getLong("answer_id"));
                ua.setTextAnswer(rs.getString("text_answer"));
                userAnswers.add(ua);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAnswers;
    }
}