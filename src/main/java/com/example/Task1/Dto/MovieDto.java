package com.example.Task1.Dto;

import com.example.Task1.Model.Actor;
import com.example.Task1.Model.Director;
import lombok.Data;

import java.util.List;

@Data
public class MovieDto {
    private int id;
    private int year;
    private String title, lang, country;
    List<Director> director;
    List<Actor> actor;
}
