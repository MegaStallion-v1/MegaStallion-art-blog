package com.megastallion.Megastallion.payLoad;

import com.megastallion.Megastallion.entities.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PostDto {
    private String title;
    private String content;
    private String imageUrl;
    private String category;
}
