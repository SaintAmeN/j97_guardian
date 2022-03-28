package com.sda.j97.guardian.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUserDto {
    private Long identifier;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
}
