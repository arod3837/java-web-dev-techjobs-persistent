package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {
//Step 3
//Add a field employerRepository annotated with @Autowired

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("jobs", jobRepository.findAll());

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
            return "add";
        }

        Optional<Employer> employerObjs = employerRepository.findById(employerId);
        Employer employerObject = employerObjs.get();
        newJob.setEmployer(employerObject);

        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);


        jobRepository.save(newJob);
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional optSkill = jobRepository.findById(jobId);
        if (optSkill.isEmpty()) {
            Job job = (Job) optSkill.get();
            model.addAttribute("job", job);
            return "view";
        } else {
            return "redirect:../";

        }
    }
}