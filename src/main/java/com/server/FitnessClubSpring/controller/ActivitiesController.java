package com.server.FitnessClubSpring.controller;

import com.server.FitnessClubSpring.controller.errors.ActivitiesNotFoundException;
import com.server.FitnessClubSpring.entity.Activities;
import com.server.FitnessClubSpring.entity.Trainers;
import com.server.FitnessClubSpring.repository.ActivitiesRepository;
import com.server.FitnessClubSpring.repository.TrainersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ActivitiesController {
    
    private final ActivitiesRepository activitiesRepository;
    private final TrainersRepository trainersRepository;



    public ActivitiesController(ActivitiesRepository activitiesRepository, TrainersRepository trainersRepository) {
        this.activitiesRepository = activitiesRepository;
        this.trainersRepository = trainersRepository;
    }

    @GetMapping("/activities")
    List<Activities> all() {
        return activitiesRepository.findAll();
    }

    @PostMapping("/activities")
    public ResponseEntity<?> newActivity(@RequestBody Activities newActivity) {
        activitiesRepository.save(newActivity);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
