package com.server.FitnessClubSpring.controller;

import com.server.FitnessClubSpring.controller.errors.ActivitiesNotFoundException;
import com.server.FitnessClubSpring.entity.Activities;
import com.server.FitnessClubSpring.entity.Areas;
import com.server.FitnessClubSpring.entity.Trainers;
import com.server.FitnessClubSpring.repository.ActivitiesRepository;
import com.server.FitnessClubSpring.repository.AreasRepository;
import com.server.FitnessClubSpring.repository.TrainersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Класс контроллера для занятий, где задаются HTTP запросы.
 */
@RestController
public class ActivitiesController {
    
    private final ActivitiesRepository activitiesRepository;
    private final TrainersRepository trainersRepository;
    private final AreasRepository areasRepository;

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param activitiesRepository - репозиторий занятий
     * @param trainersRepository - репозиорий тренеров
     * @param areasRepository - репозиторий залов
     */
    public ActivitiesController(ActivitiesRepository activitiesRepository, TrainersRepository trainersRepository,
                                AreasRepository areasRepository) {
        this.activitiesRepository = activitiesRepository;
        this.trainersRepository = trainersRepository;
        this.areasRepository = areasRepository;
    }

    /**
     * GET запрос
     * @return все занятия
     */
    @GetMapping("/activities")
    List<Activities> all() {
        return activitiesRepository.findAll();
    }

    /**
     * POST запрос
     * @param newActivity - новое занятие
     * @return HTTP статус "CREATED"
     */
    @PostMapping("/activities")
    public ResponseEntity<?> newActivity(@RequestBody Activities newActivity) {
        activitiesRepository.save(newActivity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * GET запрос определенного занятия
     * @param id - id занятия
     * @return занятие по id
     */
    @GetMapping("/activities/{id}")
    Activities one(@PathVariable Long id) {

        return activitiesRepository.findById(id)
                .orElseThrow(() -> new ActivitiesNotFoundException(id));
    }

    /**
     * PUT запрос на изменение занятия
     * @param newActivity - новое занятие
     * @param id - id занятия
     * @return измененное занятие
     */
    @PutMapping("/activities/{id}")
    Activities replaceClient(@RequestBody Activities newActivity, @PathVariable Long id) {

        Optional<Trainers> trainer = trainersRepository.findById(newActivity.getTrainer().getId());
        Optional<Areas> area = areasRepository.findById(newActivity.getArea().getId());

        return activitiesRepository.findById(id)
                .map(activity -> {
                    activity.setName(newActivity.getName());
                    activity.setTrainer(trainer.get());
                    activity.setArea(area.get());
                    return activitiesRepository.save(activity);
                })
                .orElseGet(() -> {
                    newActivity.setId(id);
                    return activitiesRepository.save(newActivity);
                });
    }

    /**
     * DELETE запрос
     * @param id - id занятия
     */
    @DeleteMapping("/activities/{id}")
    void deleteClient(@PathVariable Long id) {
        activitiesRepository.deleteById(id);
    }
}
