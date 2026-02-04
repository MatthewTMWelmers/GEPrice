package com.geprice.repository;

import com.geprice.pojo.ItemDetail;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDetailRepo extends JpaRepository<@NonNull ItemDetail, @NonNull Integer> {
}