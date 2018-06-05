package com.example.carlosparra.exercise01.models;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Carlos Parra on 6/5/2018.
 */

public class User implements Serializable {
    public String name;
    public String description;
    public HashMap<String, Integer> attributes;

    public User() {
        this.name = "Carlos Parra";
        this.description = "Software Engineer with experience in Web Development and Test Automation.";
        this.attributes = new HashMap<>();
        this.attributes.put("Repositories", 125);
        this.attributes.put("Collaborations", 29);
        this.attributes.put("Stars", 755);
    }

}
