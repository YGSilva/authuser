package com.ead.authuser.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(
            @RequestBody @Validated(UserDto.UserView.RegistationPost.class) @JsonView(UserDto.UserView.RegistationPost.class) UserDto userDto) {

        if (userService.existsByUsername(userDto.getUsername()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR: Username is Already Taken!");

        if (userService.existsByEmail(userDto.getEmail()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR: Email is Already Taken!");

        var userModel = new UserModel();
        // CONVERTE O DTO NA CLASSE, NAO PRECISO MAIS CRIAR A ARROW FUNCTION OU FOREACH
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

        userService.save(userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

}
