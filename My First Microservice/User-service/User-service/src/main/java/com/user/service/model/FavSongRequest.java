package com.user.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavSongRequest {
    private String songId;
    private String userId;
}
