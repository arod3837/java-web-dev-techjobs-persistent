package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @OneToMany

    @JoinColumn
//    Step 3
//    add a private property jobs of type List<Job>
//    and initialize it to an empty ArrayList.
    private List<Job> jobs = new ArrayList<>();

    // Step 2
    // string field for location with validation

    @Size(min = 3, max = 30)
    @NotBlank(message = "Location is required")
    private String location;

    public Employer() {

    }

    //add getters and setters

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
