package testsystem.service;

import testsystem.model.User;
import testsystem.repository.UserRepository;
import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public List<User> getAllUsers(int page, int size) {
        return userRepository.findAll(page, size);
    }

    public int getTotalUsersCount() {
        return userRepository.count();
    }

    public void createUser(User user) {
        // Валидация имени пользователя
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым");
        }
        // Валидация email (простая проверка на наличие @)
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Некорректный email");
        }
        userRepository.insert(user);
    }

    public void updateUser(User user) {
        // Валидация ID
        if (user.getId() == null || user.getId() <= 0) {
            throw new IllegalArgumentException("ID пользователя не может быть пустым или отрицательным");
        }
        // Валидация имени
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым");
        }
        // Валидация email
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Некорректный email");
        }
        userRepository.update(user);
    }

    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    public List<User> searchUsers(String keyword, int page, int size) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllUsers(page, size);
        }
        return userRepository.search(keyword, page, size);
    }
}