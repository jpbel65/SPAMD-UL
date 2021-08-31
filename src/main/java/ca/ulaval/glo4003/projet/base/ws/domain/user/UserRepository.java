package ca.ulaval.glo4003.projet.base.ws.domain.user;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findById(String id);

    void save(User user);
}
