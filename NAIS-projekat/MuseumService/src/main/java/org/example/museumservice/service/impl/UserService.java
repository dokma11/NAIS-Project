package org.example.museumservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.museumservice.core.service.impl.CRUDService;
import org.example.museumservice.dto.EmployeeResponseDTO;
import org.example.museumservice.dto.UserResponseDTO;
import org.example.museumservice.model.User;
import org.example.museumservice.service.IUserService;
import org.example.museumservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService extends CRUDService<User, Integer> implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        super(repository);
        this.userRepository = repository;
    }

    @Override
    public User findByUsername(String username) throws NoSuchElementException {
        return userRepository.findByUsername(username).orElseThrow();
    }

    public UserResponseDTO getById(Integer userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + userId));
        return convertToUserResponseDTO(user);
    }

    private EmployeeResponseDTO convertToEmployeeResponseDTO(User user) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getFirstName() + " " + user.getLastName());
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail());
        dto.setIsAccountLocked(user.getIsAccountLocked());
        // Add more fields as needed, for example, dto.setIsBlocked(user.getIsBlocked());
        return dto;
    }

    private UserResponseDTO convertToUserResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail());
        // Add more fields as needed, for example, dto.setIsBlocked(user.getIsBlocked());
        return dto;
    }

}
