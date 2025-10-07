package testsystem.repository;

import testsystem.model.User;
import java.util.List;

public interface UserRepositoryInterface {
    List<User> findAll(int page, int size);
    int count();
    void insert(User user);
    void update(User user);
    void delete(long id);
    List<User> search(String keyword, int page, int size);
}