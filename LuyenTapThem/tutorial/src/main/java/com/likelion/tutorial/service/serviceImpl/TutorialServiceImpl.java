package com.likelion.tutorial.service.serviceImpl;
import com.likelion.tutorial.dto.TutorialDTO;
import com.likelion.tutorial.entity.Tutorial;
import com.likelion.tutorial.repository.TutorialRepository;
import com.likelion.tutorial.service.TutorialService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialServiceImpl implements TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public TutorialDTO addTutorial(TutorialDTO tutorialDTO) {
        return  modelMapper.map(tutorialRepository.save(modelMapper.map(tutorialDTO,Tutorial.class)), TutorialDTO.class);
    }

    @Override
    public List<TutorialDTO> getAllTutorial() {
        return modelMapper.map(tutorialRepository.findAll(),new TypeToken<List<TutorialDTO>>(){
        }.getType());
    }

    @Override
    public TutorialDTO findById(long id) {
        return modelMapper.map(tutorialRepository.findById(id).orElse(null),TutorialDTO.class);
    }



    @Override
    public void deleteById(long id) {
        tutorialRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
         tutorialRepository.deleteAll();
    }

    @Override
    public List<TutorialDTO> getAllByPublished(Boolean published) {
        return modelMapper.map(tutorialRepository.findByPublished(published),new TypeToken<List<TutorialDTO>>(){
        }.getType());
    }

    @Override
    public List<TutorialDTO> getAllByTittle(String title) {
        return modelMapper.map((tutorialRepository.findByTitleContaining(title)),new TypeToken<List<TutorialDTO>>(){
        }.getType());
    }
}
