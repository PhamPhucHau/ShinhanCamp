package com.likelion.tutorial.repository;

import com.likelion.tutorial.entity.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
// Function auto create read, update, delete operation for object Tutorial
public interface TutorialRepository extends JpaRepository<Tutorial,Long > {
    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);
}
