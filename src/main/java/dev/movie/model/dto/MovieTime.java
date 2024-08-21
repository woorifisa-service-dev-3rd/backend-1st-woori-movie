package dev.movie.model.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class MovieTime {
	private Long id;
	private String time;
}

