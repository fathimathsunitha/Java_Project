package com.example.Task1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="actor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    @Id
    private Integer id;

    private String actfname;

    private String actlname;

}
