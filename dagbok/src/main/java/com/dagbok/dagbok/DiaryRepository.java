package com.dagbok.dagbok;


import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

public interface DiaryRepository extends CrudRepository<Diary, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Diary d SET d.title = ?1, d.post = ?2, d.date = ?3 WHERE d.id = ?4")
    int updatePost(String newTitle, String newPost, Date newDate, int id);
    
}
