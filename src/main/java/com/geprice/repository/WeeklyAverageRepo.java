package com.geprice.repository;

import com.geprice.pojo.WeeklyAverage;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyAverageRepo extends JpaRepository<@NonNull WeeklyAverage, @NonNull Integer> {
}
