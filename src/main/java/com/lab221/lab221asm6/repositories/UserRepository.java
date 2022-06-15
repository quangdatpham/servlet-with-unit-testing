package com.lab221.lab221asm6.repositories;

import com.lab221.lab221asm6.entities.User;
import com.lab221.lab221asm6.utils.StringUtil;

import java.sql.*;
import java.util.List;

public class UserRepository implements IRepository<User, Integer> {

    /**
     * database connection.
     */
    Connection connection;

    /**
     * constructor.
     * @param connection the database connection
     */
    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(User entity) {
        String sql = "INSERT INTO users (id, username, password, fullname, is_first_time_login,is_locked, attempt)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getUsername());
            statement.setString(3, entity.getPassword());
            statement.setString(4, entity.getFullname());
            statement.setBoolean(5, entity.isFirstTimeLogin());
            statement.setBoolean(6, entity.isLocked());
            statement.setInt(7, entity.getAttempt());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    /**
     * Update security questions and answers.
     * @param userId the userId
     * @param questions the question list
     * @param answers the answers list
     * @return true if updated
     */
    public boolean updateSecurityQuestionForFirstLogin(Integer userId, List<String> questions, List<String> answers) {
        String sql = "UPDATE users SET question1=?, answer1=?, question2=?," +
                "answer2=?, question3=?, answer3=?, is_first_time_login=FALSE WHERE id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            if (!StringUtil.isBlank(questions.get(0))) {
                statement.setString(1, questions.get(0));
                statement.setString(2, answers.get(0));
            } else {
                statement.setString(1, "");
                statement.setString(2, "");
            }
            if (!StringUtil.isBlank(questions.get(1))) {
                statement.setString(3, questions.get(1));
                statement.setString(4, answers.get(1));
            } else {
                statement.setString(3, "");
                statement.setString(4, "");
            }
            if (!StringUtil.isBlank(questions.get(2))) {
                statement.setString(5, questions.get(2));
                statement.setString(6, answers.get(2));
            } else {
                statement.setString(5, "");
                statement.setString(6, "");
            }
            statement.setInt(7, userId);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM users WHERE id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> search(String keyword) {
        return null;
    }

    /**
     * Update login attempt.
     * @param id the userId
     * @param attempt the number of attempt
     * @return true if updated
     */
    public boolean updateLoginAttempt(Integer id, int attempt) {
        String sql = "UPDATE users SET attempt=?, is_locked=? WHERE id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, attempt);
            statement.setBoolean(2, attempt >= 3);
            statement.setInt(3, id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Find a user by username.
     * @param username the username
     * @return user if found
     */
    public User findByUsername(String username) {
        String sql = "SELECT id, username, fullname, password, attempt, is_locked," +
                "question1, answer1, question2, answer2, question3, answer3," +
                "is_first_time_login FROM users WHERE username=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                User user = new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("fullname"),
                        result.getString("password"),
                        result.getInt("attempt"),
                        result.getBoolean("is_locked"),
                        result.getBoolean("is_first_time_login"));
                user.setQuestion1(result.getString("question1"));
                user.setAnswer1(result.getString("answer1"));
                user.setQuestion2(result.getString("question2"));
                user.setAnswer2(result.getString("answer2"));
                user.setQuestion3(result.getString("question3"));
                user.setAnswer3(result.getString("answer3"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Update password.
     * @param newPassword the new password
     * @param userId the userId
     * @return true if updated
     */
    public boolean updatePassword(String newPassword, Integer userId) {
        String sql = "UPDATE users SET password=? WHERE id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setInt(2, userId);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
