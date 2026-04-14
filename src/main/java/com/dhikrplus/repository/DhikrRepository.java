package com.dhikrplus.repository;

import com.dhikrplus.entity.Dhikr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DhikrRepository extends JpaRepository<Dhikr, Long> {
}
