package com.example.battleships.repository;

import com.example.battleships.model.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    boolean existsByName(String name);

    Set<Ship> findAllByUser_Id(Long id);

    Set<Ship> findAllByUser_IdIsNot(Long id);

    @Query("SELECT s FROM Ship AS s ORDER BY s.name, s.health, s.power")
    List<Ship> findAllShipsOrderByTheirStatus();

    Ship findByName(String name);
}
