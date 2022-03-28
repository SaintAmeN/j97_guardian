package com.sda.j97.guardian.service;

import com.sda.j97.guardian.model.dto.ApplicationUserDto;
import com.sda.j97.guardian.model.mapper.ApplicationUserMapper;
import com.sda.j97.guardian.repository.ApplicationUserRepository;
import com.sda.j97.guardian.repository.ApplicationUserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {
    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationUserRoleRepository applicationUserRoleRepository;
    private final ApplicationUserMapper applicationUserMapper;

    public List<ApplicationUserDto> getAll() {
        return applicationUserRepository.findAll()
                .stream()
                .map(applicationUserMapper::userToDto)
                .collect(Collectors.toList());
    }
}
