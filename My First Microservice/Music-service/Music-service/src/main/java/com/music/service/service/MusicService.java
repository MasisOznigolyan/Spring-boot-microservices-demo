package com.music.service.service;

import com.music.service.dto.MusicDto;
import com.music.service.model.Music;
import com.music.service.repository.MusicRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicService {

    private final MusicRepository musicRepository;

    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public void addMusic(MusicDto musicDto){
        Music music= new Music(musicDto.getId(),musicDto.getName(),musicDto.getArtistName());

        musicRepository.save(music);
    }

    public MusicDto getMusicById(String id){
        Music m=musicRepository.findById(id).orElseThrow();

        return new MusicDto(m.getId(),m.getName(),m.getArtistName());
    }

    public List<MusicDto> getAllMusics(){
        return musicRepository.findAll().stream().map(music -> new MusicDto(music.getId(), music.getName(), music.getArtistName())).collect(Collectors.toList());
    }


}
