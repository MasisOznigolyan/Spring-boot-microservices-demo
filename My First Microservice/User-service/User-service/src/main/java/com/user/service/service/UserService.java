package com.user.service.service;

import com.user.service.client.MusicServiceClient;
import com.user.service.dto.MusicDto;
import com.user.service.dto.UserDto;
import com.user.service.model.FavSongRequest;
import com.user.service.model.User;
import com.user.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final MusicServiceClient musicServiceClient;
    public UserService(UserRepository userRepository, MusicServiceClient musicServiceClient) {
        this.userRepository = userRepository;
        this.musicServiceClient = musicServiceClient;
    }

    public void createUser(UserDto userDto){
        User user= new User(userDto.getId(),userDto.getFullName(),userDto.getEmail(),userDto.getPassword(),null);
        userRepository.save(user);

    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            List<MusicDto> favSongs = (user.getFavSongs() == null ? new ArrayList<>() : user.getFavSongs().stream()
                    .map(songId -> musicServiceClient.getMusicById(songId).getBody())
                    .collect(Collectors.toList()));

            return new UserDto(user.getId(), user.getFullName(), user.getEmail(), user.getPassword(), favSongs);
        }).collect(Collectors.toList());
    }



    public UserDto getUserById(String id){
        User u = userRepository.findById(id).orElseThrow();

        // Initialize UserDto with an empty list for favSongs
        UserDto dto = new UserDto(u.getId(), u.getFullName(), u.getEmail(), u.getPassword(), new ArrayList<>());

        // Map favSongs from User to UserDto
        List<MusicDto> favSongs = u.getFavSongs().stream()
                .map(songId -> musicServiceClient.getMusicById(songId).getBody())
                .collect(Collectors.toList());

        dto.setFavSongs(favSongs);
        return dto;
    }

    public void addFavSong(FavSongRequest req) {
        MusicDto musicDto = musicServiceClient.getMusicById(req.getSongId()).getBody();

        Optional<User> userOptional = userRepository.findById(req.getUserId());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid userId");
        }


        User user = userOptional.get();
        log.info("Retrieved user with ID: " + req.getUserId() + " and favSongs: " + user.getFavSongs());

        if (user.getFavSongs() == null) {
            log.warn("favSongs list is null, initializing...");
            user.setFavSongs(new ArrayList<>());
        }

        user.getFavSongs().add(musicDto.getId());
        userRepository.save(user);
        log.info("Added song ID: " + musicDto.getId() + " to user's favorite songs.");
    }
}
