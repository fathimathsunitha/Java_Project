package com.example.Task1.Repository;

import com.example.Task1.Model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    Movie findByTitle(String title);

    List<Movie> findByYearGreaterThan(Integer year);

    @Transactional
    void deleteByActor_actfnameAndActor_actlname(String fname, String lname);

    List<Movie> findByCountryAndDirector_dirfnameAndDirector_dirlnameAndYearGreaterThanEqual(
            String country, String Fname, String Lname, Integer year);
}
