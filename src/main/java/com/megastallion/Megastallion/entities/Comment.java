package com.megastallion.Megastallion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity{

    private String content;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;
}
