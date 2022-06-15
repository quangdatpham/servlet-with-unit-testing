package com.lab221.lab221asm6.services;

import com.lab221.lab221asm6.exceptions.UserLockedException;
import com.lab221.lab221asm6.entities.User;
import com.lab221.lab221asm6.models.UserModel;
import com.lab221.lab221asm6.repositories.UserRepository;

import java.util.List;

public class UserService {

    /**
     * The user repository.
     */
    public UserRepository userRepository;

    /**
     * The number of try for login.
     */
    private static final int MAXIMUM_LOGIN_RETRY = 3;

    /**
     * Init UserService.
     * @param userRepository the userRepository
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create new user.
     * @param userModel the user model
     * @return true if succeed
     */
    public boolean create(UserModel userModel) {
        return userRepository.create(userModel.toEntity());
    }

    /**
     * Update security question.
     * @param userId the userId
     * @param questions the questions
     * @param answers the answers
     * @return true if succeed
     */
    public boolean updateSecurityQuestionForFirstLogin(Integer userId, List<String> questions, List<String> answers) {
        return userRepository.updateSecurityQuestionForFirstLogin(userId, questions, answers);
    }

    /**
     * Compare password.
     * @param username the username
     * @param password the password
     * @return true if correct password
     */
    public UserModel comparePassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            if (user.isLocked()) {
                throw new UserLockedException();
            }
            if (user.getPassword().equals(password)) {
                userRepository.updateLoginAttempt(user.getId(), 0);
                return user.toModel();
            }
            userRepository.updateLoginAttempt(user.getId(), user.getAttempt() + 1);
            if (user.getAttempt() + 1 >= MAXIMUM_LOGIN_RETRY) {
                throw new UserLockedException();
            }
        }
        return null;
    }

    /**
     * Change password.
     * @param newPassword the newPassword
     * @param userId the userId
     */
    public void changePassword(String newPassword, Integer userId) {
        userRepository.updatePassword(newPassword, userId);
    }

    /**
     * Delete a user.
     * @param id the userId
     */
    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
