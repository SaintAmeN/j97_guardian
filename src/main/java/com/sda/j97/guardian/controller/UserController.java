package com.sda.j97.guardian.controller;

import com.sda.j97.guardian.model.dto.ApplicationUserDto;
import com.sda.j97.guardian.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UsersService usersService;

    @GetMapping
    public List<ApplicationUserDto> get(){
        return usersService.getAll();
    }
}
