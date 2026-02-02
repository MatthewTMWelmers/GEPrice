package com.geprice.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boss_item")
@IdClass(BossItemKey.class)
public class BossItem {
    @Id
    @Column(name = "boss_id")
    private int bossId;

    @Id
    @Column(name = "item_id")
    private int itemId;
}
