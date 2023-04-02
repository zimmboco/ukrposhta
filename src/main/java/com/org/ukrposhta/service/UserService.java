package com.org.ukrposhta.service;

import com.org.ukrposhta.model.User;
import java.util.List;

public interface UserService {
    User save(User user);

    User getByID(Long id);

    void deleteById(Long id);

    User findByEmail(String email);

    List<User> getAll();

    User update(User user);
    List<User> findAllByProjects_Id(Long id);
}
