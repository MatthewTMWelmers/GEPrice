package com.geprice.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_detail")
public class ItemDetail {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Long value;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "cwa")
    private Long currentWeekAverage;

    @Column(name = "pwa")
    private Long previousWeekAverage;

    public long getCurrentWeekAverage() {
        return currentWeekAverage != null ? currentWeekAverage : 0;
    }

    public long getPreviousWeekAverage() {
        return previousWeekAverage != null ? previousWeekAverage : 0;
    }
}
