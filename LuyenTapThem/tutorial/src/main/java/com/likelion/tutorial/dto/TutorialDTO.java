package com.likelion.tutorial.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TutorialDTO {
    private  long id;
    private String title;
    private boolean published;
    private String description;
}
