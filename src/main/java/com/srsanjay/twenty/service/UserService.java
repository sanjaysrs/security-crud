package com.srsanjay.twenty.service;

import com.srsanjay.twenty.dto.UserDto;
import com.srsanjay.twenty.model.User;
import com.srsanjay.twenty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void save(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("ROLE_USER");
        user.setEnabled(true);
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public void disableById(Integer id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty())
            return;

        User user = userOptional.get();
        user.setEnabled(false);
        userRepository.save(user);
    }

    public void enableById(Integer id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty())
            return;

        User user = userOptional.get();
        user.setEnabled(true);
        userRepository.save(user);
    }

}
