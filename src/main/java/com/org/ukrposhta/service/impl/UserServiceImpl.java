package com.org.ukrposhta.service.impl;

import com.org.ukrposhta.model.User;
import com.org.ukrposhta.repository.UserRepository;
import com.org.ukrposhta.service.UserService;
import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getByID(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found"));
    }
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Can`t find user"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        User user1 = new User();
        user1.setId(user.getId());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setRoles(user.getRoles());
        user1.setLevels(user.getLevels());
        user1.setProjects(user.getProjects());
        return userRepository.save(user1);
    }

    @Override
    public List<User> findAllByProjects_Id(Long id) {
        return userRepository.findAllByProjects_Id(id);
    }
}
