package com.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeQouteResponse {
    private String status;
    private AnimeData data;




}
@Builder
@Data
class AnimeData {
    private String content;
    private Anime anime;
    private Character character;
}

@Builder
@Data
class Anime {
    private int id;
    private String name;

    private String altName;
}


@Builder
@Data
class Character {
    private int id;
    private String name;

}


