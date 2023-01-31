package com.megastallion.Megastallion.entities;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity{

    private String name;

    @OneToMany(mappedBy = "category", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Post> posts;

}
