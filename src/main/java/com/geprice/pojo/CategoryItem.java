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
@Table(name = "category_item")
@IdClass(CategoryItemKey.class)
public class CategoryItem {
    @Id
    @Column(name = "category_id")
    private int categoryId;

    @Id
    @Column(name = "item_id")
    private int itemId;
}
