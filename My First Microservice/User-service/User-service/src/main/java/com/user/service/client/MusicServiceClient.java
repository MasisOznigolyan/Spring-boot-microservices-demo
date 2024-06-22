package com.user.service.client;

import com.user.service.dto.MusicDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "Music-service", path = "/v1/music")
public interface MusicServiceClient {
    @GetMapping("/")
    public ResponseEntity<List<MusicDto>> getAllMusic();

    @GetMapping("/{id}")
    public ResponseEntity<MusicDto> getMusicById(@PathVariable("id") String id);
    @PostMapping("/")
    public ResponseEntity<Void> addMusic(@RequestBody MusicDto musicDto);
}
