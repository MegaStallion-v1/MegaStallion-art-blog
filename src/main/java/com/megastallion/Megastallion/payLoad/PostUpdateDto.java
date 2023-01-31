package com.megastallion.Megastallion.payLoad;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateDto {

    private String title;
    private String content;
    private String imageUrl;
    private Long categoryId;
}
