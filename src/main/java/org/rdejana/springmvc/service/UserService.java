package org.rdejana.springmvc.service;

import org.rdejana.springmvc.dto.RegistrationDto;
import org.rdejana.springmvc.model.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
