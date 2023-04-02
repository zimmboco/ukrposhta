package com.org.ukrposhta.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.org.ukrposhta.model.User;
import com.org.ukrposhta.repository.UserRepository;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userOptional = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Can`t find user"));
        return  withUsername(username)
                .password(userOptional.getPassword())
                .authorities(userOptional.getRoles().stream()
                        .map(r -> r.getRoleName().name()).toArray(String[]::new))
                .build();
    }
}
