package com.dagbok.dagbok;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

public interface DiaryRepository extends CrudRepository<Diary, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Diary d SET d.title = ?1, d.post = ?2, d.date = ?3 WHERE d.id = ?4")
    int updatePost(String newTitle, String newPost, Date newDate, int id);
    
    
    // @Query("SELECT d FROM Diary WHERE DATE(*.date) = CURRENT_DATE")
    // List<Diary> findCurrentDate();

    @Query("SELECT d FROM Diary d WHERE d.date <= CURDATE()")
    List<Diary> findCurrentDate();

     @Query("SELECT d FROM Diary d WHERE d.date BETWEEN ?1 AND ?2")
    List<Diary> findDatesBetween(Date toDate, Date fromDate);

}
