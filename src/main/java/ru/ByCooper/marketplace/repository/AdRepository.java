package ru.ByCooper.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ByCooper.marketplace.entity.Ad;

import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    Optional<Ad> findById(long id);
}