package nix.exercise.one.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nix.exercise.one.domain.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
