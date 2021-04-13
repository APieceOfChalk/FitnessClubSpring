package com.server.FitnessClubSpring.controller;

import com.server.FitnessClubSpring.controller.errors.SubscriptionsNotFoundException;
import com.server.FitnessClubSpring.entity.Subscriptions;
import com.server.FitnessClubSpring.repository.SubscriptionsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubscriptionsController {

    private final SubscriptionsRepository subscriptionsRepository;

    public SubscriptionsController(SubscriptionsRepository subscriptionsRepository) {
        this.subscriptionsRepository = subscriptionsRepository;
    }

    @GetMapping("/subscriptions")
    List<Subscriptions> all() {
        return subscriptionsRepository.findAll();
    }

    @PostMapping("/subscriptions")
    Subscriptions newSubscription(@RequestBody Subscriptions newSubscription) {
        return subscriptionsRepository.save(newSubscription);
    }

    @GetMapping("/subscriptions/{id}")
    Subscriptions one(@PathVariable Long id) {

        return subscriptionsRepository.findById(id)
                .orElseThrow(() -> new SubscriptionsNotFoundException(id));
    }

    @PutMapping("/subscriptions/{id}")
    Subscriptions replaceClient(@RequestBody Subscriptions newSubscription, @PathVariable Long id) {

        return subscriptionsRepository.findById(id)
                .map(subscription -> {
                    subscription.setClient(newSubscription.getClient());
                    subscription.setActivity(newSubscription.getActivity());
                    subscription.setDate(newSubscription.getDate());
                    subscription.setPrice(newSubscription.getPrice());
                    subscription.setPaymentStatus(newSubscription.getPaymentStatus());
                    return subscriptionsRepository.save(subscription);
                })
                .orElseGet(() -> {
                    newSubscription.setId(id);
                    return subscriptionsRepository.save(newSubscription);
                });
    }

    @DeleteMapping("/subscriptions/{id}")
    void deleteClient(@PathVariable Long id) {
        subscriptionsRepository.deleteById(id);
    }
}
