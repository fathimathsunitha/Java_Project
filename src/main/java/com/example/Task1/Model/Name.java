package com.example.Task1.Model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Name {
    private String fName;
    private String lName;

    public static Name createFromName(String requestName) throws IndexOutOfBoundsException {
        String[] names = requestName.split("\s+", 2);
        Name name = new Name();
        name.setFName(names[0]);
        name.setLName(names[1]);
        return name;
    }

    public String displayName() {
        return fName + " " + lName;
    }
}
