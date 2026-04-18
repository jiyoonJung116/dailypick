package com.project.dailypick.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dailypick.dto.UserDto;
import com.project.dailypick.mapper.UserMapper;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto getUserById(long id) {
        return userMapper.getUserById(id);
    }

    public long createUser(UserDto userDto) {
        String phoneNumber = userDto.getPhoneNumber();
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            phoneNumber = phoneNumber.replaceAll("[^0-9]", "").replaceAll("^(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
            userDto.setPhoneNumber(phoneNumber);
        }

        if (userDto.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(encodedPassword);
        }

        long userId = userMapper.createUser(userDto);

        return userId;
    }

    public long login(String userId, String password) {
        UserDto user = userMapper.getUserByUserId(userId);

        if (user == null) {
            return 0;
        }
        
        if(passwordEncoder.matches(password, user.getPassword())) {
            return user.getId();
        } else {
            return 0;
        }
    }
}
