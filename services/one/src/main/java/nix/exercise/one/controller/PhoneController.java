package nix.exercise.one.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nix.exercise.one.PhoneSearchApplication;
import nix.exercise.one.domain.Phone;
import nix.exercise.one.repository.PhoneRepository;

/**
 * RPC controller accepts API calls within {@link PhoneSearchApplication}.
 */
@RestController
public class PhoneController {
    @Autowired
    private PhoneRepository phoneRepository;

    @GetMapping("/phones/all")
    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    @GetMapping("/phone/{article}")
    public ResponseEntity<BigDecimal> getPhonePrice(@PathVariable String article) {
        var phone = new Phone();
        phone.setArticle(article);

        return phoneRepository.findOne(Example.of(phone)).map(Phone::getPrice)
                              .map(ResponseEntity::ok)
                              .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
