package com.example.Task1.Service;

import com.example.Task1.Dto.MovieDirectorDto;
import com.example.Task1.Dto.MovieDto;
import com.example.Task1.Dto.MovieNameDto;

import java.util.List;

public interface MovieService {
    MovieDto saveMovie(MovieDto movieDto);

//    List<MovieDto> findAllMovie();

    MovieDto updateMovie(MovieDto movieDto);

//    void deleteMovie(int id);

    MovieDirectorDto findMovieByTitle(String title);

    List<MovieNameDto> findByYearGreaterThan(Integer year);

    List<MovieDirectorDto> findByCountryAndDirectorAndYearGreaterThanEqual(
            String country,
            String directorName,
            Integer year
    );

    void deleteByActor(String actName);
}
