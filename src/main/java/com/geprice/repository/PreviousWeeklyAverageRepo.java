package com.geprice.repository;

import com.geprice.pojo.PreviousWeeklyAverage;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreviousWeeklyAverageRepo extends JpaRepository<@NonNull PreviousWeeklyAverage, @NonNull Integer> {
}
