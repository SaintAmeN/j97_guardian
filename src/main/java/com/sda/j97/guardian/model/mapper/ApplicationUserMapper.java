package com.sda.j97.guardian.model.mapper;

import com.sda.j97.guardian.model.ApplicationUser;
import com.sda.j97.guardian.model.dto.ApplicationUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ApplicationUserMapper {

    @Mappings({
            @Mapping(target = "identifier", source = "id"),
            @Mapping(target = "name", source = "firstName"),
            @Mapping(target = "surname", source = "lastName"),
            @Mapping(target = "dateOfBirth", source = "dateOfBirth"),
    })
    ApplicationUserDto userToDto(ApplicationUser entity);
}
