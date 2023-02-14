package com.likelion.tutorial.Controller;

import com.likelion.tutorial.dto.TutorialDTO;
import com.likelion.tutorial.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api")
public class TutorialController {
    @Autowired
    TutorialService tutorialService;
    @GetMapping("/tutorials")
    public ResponseEntity<List<TutorialDTO>> getAllTutorials(@RequestParam(required=false) String title)
    {
        try{
                List<TutorialDTO> tutorials=new ArrayList<TutorialDTO>();
                if(title==null)
                {
                    tutorials=tutorialService.getAllTutorial();

                }
                else
                {
                    tutorials=tutorialService.getAllByTittle(title);
                }
                if(tutorials.isEmpty())
                {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(tutorials,HttpStatus.OK);
        }
        catch(Exception ex)
        {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<TutorialDTO> getTutorialById(@PathVariable("id") long id)
    {
        TutorialDTO tutorialDTO=tutorialService.findById(id);
        if(tutorialDTO!=null)
        {
            return new ResponseEntity<>(tutorialDTO,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @PostMapping("/tutorials")
    public ResponseEntity<TutorialDTO> createTutorial(@RequestBody TutorialDTO tutorialDTO)
    {
        try{
            TutorialDTO newTotorial=new TutorialDTO();
            newTotorial=tutorialService.addTutorial(tutorialDTO);
            return new ResponseEntity<>(newTotorial,HttpStatus.OK);

        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/tutorials/{id}")
    public ResponseEntity<TutorialDTO> updateTutorial(@PathVariable(name="id") long id,@RequestBody TutorialDTO tutorialDTO)
    {
        TutorialDTO _tutorial=tutorialService.findById(id);
        if(_tutorial!=null)
        {
            _tutorial.setTitle(tutorialDTO.getTitle());
            _tutorial.setPublished(tutorialDTO.isPublished());
            _tutorial.setDescription(tutorialDTO.getDescription());
            return new ResponseEntity<>(tutorialService.addTutorial(_tutorial),HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/tutorials")
    public ResponseEntity deleteAllTutorial()
    {
        try{
            tutorialService.deleteAll();
            return new ResponseEntity<>(true,HttpStatus.NO_CONTENT);
        }
        catch(Exception ex)
        {
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity deleteTutorialById(@PathVariable(name="id") Long id)
    {

        try{
            tutorialService.deleteById(id);
            return new ResponseEntity<>(true,HttpStatus.NO_CONTENT);
        }
        catch(Exception ex)
        {
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<TutorialDTO>> getAllTutorialPublished()
    {
        List<TutorialDTO> tutorials=new ArrayList<>();
        try{

            tutorials=tutorialService.getAllByPublished(true);
           if(tutorials!=null) {
               return new ResponseEntity<>(tutorials, HttpStatus.OK);
           }
           else {
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           }
        }
        catch(Exception ex)
        {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
