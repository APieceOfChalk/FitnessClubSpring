package com.server.FitnessClubSpring.controller;

import com.server.FitnessClubSpring.controller.errors.ActivitiesNotFoundException;
import com.server.FitnessClubSpring.entity.Activities;
import com.server.FitnessClubSpring.repository.ActivitiesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivitiesController {
    
    private final ActivitiesRepository activitiesRepository;
    
    public ActivitiesController(ActivitiesRepository activitiesRepository) {
        this.activitiesRepository = activitiesRepository;
    }

    @GetMapping("/activities")
    List<Activities> all() {
        return activitiesRepository.findAll();
    }

    @PostMapping("/activities")
    Activities newActivity(@RequestBody Activities newActivity) {
        return activitiesRepository.save(newActivity);
    }

    @GetMapping("/activities/{id}")
    Activities one(@PathVariable Long id) {

        return activitiesRepository.findById(id)
                .orElseThrow(() -> new ActivitiesNotFoundException(id));
    }

    @PutMapping("/activities/{id}")
    Activities replaceClient(@RequestBody Activities newActivity, @PathVariable Long id) {

        return activitiesRepository.findById(id)
                .map(activity -> {
                    activity.setName(newActivity.getName());
                    activity.setTrainer(newActivity.getTrainer());
                    activity.setArea(newActivity.getArea());
                    return activitiesRepository.save(activity);
                })
                .orElseGet(() -> {
                    newActivity.setId(id);
                    return activitiesRepository.save(newActivity);
                });
    }

    @DeleteMapping("/activities/{id}")
    void deleteClient(@PathVariable Long id) {
        activitiesRepository.deleteById(id);
    }
}
