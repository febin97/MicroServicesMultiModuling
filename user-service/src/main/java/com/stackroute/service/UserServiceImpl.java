package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistException {
        User savedUser = null;
        if(userRepository.existsById(user.getId())){
            throw new UserAlreadyExistException("User already exist");
        }

        else{
            savedUser = userRepository.save(user);
            if(savedUser == null){
                throw new UserAlreadyExistException("User already exist");
            }
        }

         return savedUser;



    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();


    }
}
