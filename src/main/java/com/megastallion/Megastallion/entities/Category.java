package com.megastallion.Megastallion.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category extends BaseEntity{

    private String name;

    @OneToMany(mappedBy = "category", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Post> posts;

}
