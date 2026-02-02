package com.geprice.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boss")
public class Boss {
    @Id
    @NonNull
    @Column(name = "id")
    private Integer id;

    @NonNull
    @Column(name = "name")
    private String name;

    @Column(name = "wiki_url")
    private String wikiUrl;

    @Column(name = "icon")
    private String icon;
}
