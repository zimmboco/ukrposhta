package com.org.ukrposhta.service;

import com.org.ukrposhta.model.User;

public interface AuthenticationService {
    User register(com.org.ukrposhta.dto.User user);
}
