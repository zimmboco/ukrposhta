package com.org.ukrposhta.repository;

import com.org.ukrposhta.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    Level findByLevelName(String levelName);
}
