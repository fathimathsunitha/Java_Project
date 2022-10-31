package com.example.Task1.Controll;

import com.example.Task1.Dto.MovieDirectorDto;
import com.example.Task1.Dto.MovieDto;
import com.example.Task1.Dto.MovieNameDto;
import com.example.Task1.Service.MovieService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@CrossOrigin("*")
public class MovieControl {
    @Autowired
    private MovieService serviceimpl;

    @PutMapping(value = "/updateMovie")
    public MovieDto update(@RequestBody MovieDto movieDto) {
        return serviceimpl.updateMovie(movieDto);
    }

    @PostMapping(value = "/saveMovie")
    public MovieDto save(@RequestBody MovieDto movieDto) {
        return serviceimpl.saveMovie(movieDto);
    }

    @GetMapping("/getMovieByTitle/{title}")
    public MovieDirectorDto getMovieTitle(@PathVariable("title") String title) {
        return serviceimpl.findMovieByTitle(title);
    }

    @GetMapping("/getMovieByYear/{year}")
    public List<MovieNameDto> getMovieYearGreaterThan(@PathVariable("year") Integer year) {
        return serviceimpl.findByYearGreaterThan(year);

    }

    @GetMapping("/getMovieByCountryDirectorYear")
    public List<MovieDirectorDto> findByCountryAndDirectorAndYearGreaterThanEqual(
            @RequestParam String country, @RequestParam String name, @RequestParam Integer year) {
        return serviceimpl
                .findByCountryAndDirectorAndYearGreaterThanEqual(country, name, year);
    }

    @DeleteMapping("/deleteByActor")
    public void deleteByActor(@RequestParam String name) {
        serviceimpl.deleteByActor(name);
    }
}

