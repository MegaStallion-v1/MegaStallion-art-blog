package com.megastallion.Megastallion.payLoad;

import com.megastallion.Megastallion.entities.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    @NotEmpty
    @Size(min = 2,message = "post title should have at least two characters")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "post Content should have at least 10 characters")
    private String content;
    @NotEmpty
    private String imageUrl;
    private String category;
}
