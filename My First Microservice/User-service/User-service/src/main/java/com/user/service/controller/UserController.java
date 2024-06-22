package com.user.service.controller;

import com.user.service.dto.UserDto;
import com.user.service.model.FavSongRequest;
import com.user.service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/")
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") String id){
        return  ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/addFavMusic")
    public ResponseEntity<Void> addFavMusic(@RequestBody FavSongRequest favSongRequest){
        userService.addFavSong(favSongRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
