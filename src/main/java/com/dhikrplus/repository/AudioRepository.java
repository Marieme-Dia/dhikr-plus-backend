package com.dhikrplus.repository;

import com.dhikrplus.entity.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AudioRepository extends JpaRepository<Audio, Long> {
    List<Audio> findByCategory(String category);
}
