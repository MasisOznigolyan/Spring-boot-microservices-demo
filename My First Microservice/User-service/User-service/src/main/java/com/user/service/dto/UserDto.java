package com.user.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private String id;

    private String fullName;

    private String email;

    private String password;

    private List<MusicDto> favSongs;
}
