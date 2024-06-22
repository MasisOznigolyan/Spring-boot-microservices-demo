package com.music.service.controller;

import com.music.service.dto.MusicDto;
import com.music.service.service.MusicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/music")
public class MusicController {

    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MusicDto> >getAllMusic(){
        return ResponseEntity.ok(musicService.getAllMusics());
    }

    @GetMapping("{id}")
    public ResponseEntity<MusicDto> getMusicById(@PathVariable("id") String id){
        return ResponseEntity.ok(musicService.getMusicById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Void> addMusic(@RequestBody MusicDto musicDto){
        musicService.addMusic(musicDto);
        return ResponseEntity.ok().build();
    }
}
