package com.megastallion.Megastallion.payLoad;

import com.megastallion.Megastallion.entities.Category;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private String title;
    private String content;
    private String imageUrl;
    private String category;
}
