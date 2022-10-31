package com.example.Task1.Service;

import com.example.Task1.Dto.MovieDirectorDto;
import com.example.Task1.Dto.MovieDto;
import com.example.Task1.Dto.MovieNameDto;
import com.example.Task1.Exception.InvalidNameException;
import com.example.Task1.Exception.ResourceNotFoundException;
import com.example.Task1.Model.Movie;
import com.example.Task1.Model.Name;
import com.example.Task1.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MovieDto saveMovie(MovieDto movieDto) {
        System.out.println(movieDto.toString());
        Movie movie = convertMovieDtoToMovie(movieDto);
        Movie response = movieRepository.save(movie);

        return convertMovieToMovieDto(response);
    }

    @Override
    public MovieDto updateMovie(MovieDto movieDto) {
        // get post by id from the database
        int movieId = movieDto.getId();
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));

        Movie requestMovie = convertMovieDtoToMovie(movieDto);
        // TODO: update field depending on requestMovie

        if (requestMovie.getId() != null)
            movie.setId(requestMovie.getId());
        if (requestMovie.getYear() != null)
            movie.setYear(requestMovie.getYear());
        if (requestMovie.getTitle() != null)
            movie.setTitle(requestMovie.getTitle());
        if (requestMovie.getLang() != null)
            movie.setLang(requestMovie.getLang());
        if (requestMovie.getCountry() != null)
            movie.setCountry(requestMovie.getCountry());
        if (requestMovie.getDirector() != null)
            movie.setDirector(requestMovie.getDirector());
        if (requestMovie.getActor() != null)
            movie.setActor(requestMovie.getActor());

        Movie response = movieRepository.save(movie);
        return convertMovieToMovieDto(response);
    }

    @Override
    public MovieDirectorDto findMovieByTitle(String title) {
        Movie movie = movieRepository.findByTitle(title);
        if (movie == null)
            throw new ResourceNotFoundException("Movie", "title", title);
        return convertMovieToMovieDirectorDto(movie);
    }

    @Override
    public List<MovieNameDto> findByYearGreaterThan(Integer year) {
        List<Movie> movies = movieRepository.findByYearGreaterThan(year);
        return movies.stream()
                .map(this::convertMovieToMovieNameDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDirectorDto> findByCountryAndDirectorAndYearGreaterThanEqual(
            String country, String directorName, Integer year
    ) {
        try {
            Name name = Name.createFromName(directorName);
            List<Movie> movies = movieRepository.findByCountryAndDirector_dirfnameAndDirector_dirlnameAndYearGreaterThanEqual(
                    country,
                    name.getFName(),
                    name.getLName(),
                    year
            );

            return movies.stream()
                    .map(this::convertMovieToMovieDirectorDto)
                    .collect(Collectors.toList());
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidNameException(directorName);
        }
    }

    @Override
    public void deleteByActor(String actName) {
        Name name = Name.createFromName(actName);
        movieRepository.deleteByActor_actfnameAndActor_actlname(name.getFName(), name.getLName());
    }

    //    Converters
    private Movie convertMovieDtoToMovie(MovieDto movieDto) {
        Movie movie = new Movie();

        movie.setId(movieDto.getId());
        movie.setYear(movieDto.getYear());
        movie.setTitle(movieDto.getTitle());
        movie.setLang(movieDto.getLang());
        movie.setCountry(movieDto.getCountry());
        movie.setDirector(movieDto.getDirector());
        movie.setActor(movieDto.getActor());

        return movie;
    }

    private MovieDto convertMovieToMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();

        movieDto.setId(movie.getId());
        movieDto.setYear(movie.getYear());
        movieDto.setTitle(movie.getTitle());
        movieDto.setLang(movie.getLang());
        movieDto.setCountry(movie.getCountry());
        movieDto.setDirector(movie.getDirector());
        movieDto.setActor(movie.getActor());

        return movieDto;
    }

    private MovieDirectorDto convertMovieToMovieDirectorDto(Movie movie) {
        MovieDirectorDto mdDto = new MovieDirectorDto();

        mdDto.setDirname(movie
                .getDirector().stream()
                .map(director -> {
                    Name name = new Name();
                    name.setFName(director.getDirfname());
                    name.setLName(director.getDirlname());
                    return name.displayName();
                })
                .collect(Collectors.toList()));

        mdDto.setYear(movie.getYear());
        mdDto.setTitle(movie.getTitle());
        mdDto.setLang(movie.getLang());
        mdDto.setCountry(movie.getCountry());

        return mdDto;
    }

    private MovieNameDto convertMovieToMovieNameDto(Movie movie) {
        MovieNameDto movieNameDto = new MovieNameDto();

        movieNameDto.setTitle(movie.getTitle());

        return movieNameDto;
    }
}
