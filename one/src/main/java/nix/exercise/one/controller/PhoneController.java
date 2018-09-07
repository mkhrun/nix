package nix.exercise.one.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import nix.exercise.one.domain.Phone;
import nix.exercise.one.repository.PhoneRepository;

@RestController
public class PhoneController {
    @Autowired
    private PhoneRepository phoneRepository;

    @GetMapping("/phones/all")
    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }
}
