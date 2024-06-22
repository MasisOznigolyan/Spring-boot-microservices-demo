package com.music.service.repository;

import com.music.service.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface MusicRepository extends JpaRepository<Music,String> {
}
