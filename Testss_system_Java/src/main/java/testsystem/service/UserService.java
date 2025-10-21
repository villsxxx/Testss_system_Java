package testsystem.service;

import lombok.RequiredArgsConstructor;
import testsystem.model.User;
import testsystem.repository.BaseRepository;

import java.util.List;
@RequiredArgsConstructor
public class UserService {
    private final BaseRepository<User> userRepository;



    public List<User> getAllUsers(int page, int size) {
        return userRepository.findAll(page, size);
    }

    public int getTotalUsersCount() {
        return userRepository.count();
    }

    public void createUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым");
        }
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Некорректный email");
        }
        userRepository.insert(user);
    }

    public void updateUser(User user) {
        if (user.getId() == null || user.getId() <= 0) {
            throw new IllegalArgumentException("ID пользователя не может быть пустым или отрицательным");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым");
        }
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Некорректный email");
        }
        userRepository.update(user);
    }

    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    public List<User> searchUsers(long id) {

        return userRepository.findById(id);
    }
}