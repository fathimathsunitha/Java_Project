package com.example.Task1.Dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDirectorDto {
    private List<String> dirname;
    private Integer year;
    private String title, lang, country;
}
