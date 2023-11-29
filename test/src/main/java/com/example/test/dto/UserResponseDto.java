package com.example.test.dto;

import com.example.test.enums.TypeOfUser;
import lombok.Builder;

@Builder
public record UserResponseDto(  long id,
                                String email,
                                TypeOfUser status) {
}
