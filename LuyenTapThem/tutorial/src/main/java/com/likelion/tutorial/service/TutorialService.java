package com.likelion.tutorial.service;

import com.likelion.tutorial.dto.TutorialDTO;

import java.util.List;

// class contain function for service, loose coupling.
public interface TutorialService {
     TutorialDTO addTutorial(TutorialDTO TutorialDTO);
     List<TutorialDTO> getAllTutorial();
     TutorialDTO findById(long id);
     void deleteById(long id);
     void deleteAll();
     List<TutorialDTO> getAllByPublished(Boolean published);
     List<TutorialDTO> getAllByTittle(String title);

}
