package org.example.museumservice.service;

import org.example.museumservice.core.service.ICRUDService;
import org.example.museumservice.model.User;
import org.example.museumservice.dto.UserResponseDTO;
import java.util.NoSuchElementException;

public interface IUserService extends ICRUDService<User, Integer> {
    User findByUsername(String username) throws NoSuchElementException;
    UserResponseDTO getById(Integer userId);
}
