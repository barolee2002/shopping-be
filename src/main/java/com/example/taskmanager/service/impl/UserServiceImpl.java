package com.example.taskmanager.service.impl;

import com.example.taskmanager.config.security.JwtService;
import com.example.taskmanager.dto.request.LoginRequest;
import com.example.taskmanager.dto.request.UserDTORequest;
import com.example.taskmanager.dto.response.LoginResponse;
import com.example.taskmanager.dto.response.UserDTOResponse;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.exception.AppException;
import com.example.taskmanager.exception.Errors;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.service.UserService;
import com.example.taskmanager.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static java.time.LocalTime.now;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ModelMapper mapper = new ModelMapper();
    @Value("${expire.time}")
    private long expireTime;
    @Override
    public UserDTOResponse addUser(UserDTOResponse userDTO) {
        if( Utils.isEmptyOrNull(userDTO.getUsername()) || Utils.isEmptyOrNull(userDTO.getPassword()))
            throw new AppException(Errors.INVALID_DATA);
        if(userRepo.findFirstByUsername(userDTO.getUsername()).isPresent()){
            throw new AppException(Errors.EXIST_USER);
        }
        Date current = new Date();
        User user = User.builder()
                .name(userDTO.getName())
                .username(userDTO.getUsername())
                .createAt(current)
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        return mapper.map(userRepo.save(user), UserDTOResponse.class);
    }




    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        authenticationManager.authenticate(token);
        String jwt = jwtService.generateToken(loginRequest.getUsername());
        Optional<User> userOptional = userRepo.findByUsername(loginRequest.getUsername());
        return LoginResponse.builder()
                .username(loginRequest.getUsername())
                .name(userOptional.get().getName())
                .token(jwt)
                .expireTime(System.currentTimeMillis() + expireTime)
                .id(userOptional.get().getId())
                .build();
    }

    @Override
    public UserDTOResponse update(UserDTORequest userDTO, Long userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        if ( userOptional.isEmpty())
            throw new AppException(Errors.INVALID_DATA);
        User user = userOptional.get();
        user.setUsername(Utils.isEmptyOrNull(userDTO.getUsername()) ? user.getUsername() : userDTO.getUsername());
        user.setName(Utils.isEmptyOrNull(userDTO.getName()) ? user.getName() : userDTO.getName());
        user.setPassword(Utils.isEmptyOrNull(userDTO.getPassword()) ? user.getPassword() : passwordEncoder.encode(userDTO.getPassword()));
        Date current = new Date();
        user.setUpdateAt(current);

        return mapper.map(userRepo.save(user), UserDTOResponse.class);
    }

    @Override
    public UserDTOResponse getInfo(Long userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        if(userOptional.isEmpty())
            throw new AppException(Errors.INVALID_DATA);
        UserDTOResponse response = mapper.map(userOptional.get(), UserDTOResponse.class);
        response.setPassword("");
        return response;
    }
    @Override
    public Integer delete (Long userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        userRepo.deleteById(userOptional.get().getId());
        return 1;
    }

}
