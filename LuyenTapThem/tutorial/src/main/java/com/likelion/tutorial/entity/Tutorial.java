package com.likelion.tutorial.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity// Auto tạo....
@Setter// Auto generate constructor set for all variable
@Getter // // Auto generate constructor get for all variable
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial {
    @Id // Id for the class entity
    @GeneratedValue()// Auto create id for insert
    private long id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="published")
    private Boolean published;
    @Override
   public  String toString()
    {
        return "Tutorial [id="+id+"]"+", title= "+title+" , description= "+description+", published= "+published;
    }
}
