package ru.mtuci.simpleapi1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.mtuci.simpleapi1.model.Drivers;

@Transactional(readOnly = true)
public interface DriversRepository extends JpaRepository<Drivers, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Drivers p WHERE p.id=:id")
    int delete(@Param("id") Long id);
}

