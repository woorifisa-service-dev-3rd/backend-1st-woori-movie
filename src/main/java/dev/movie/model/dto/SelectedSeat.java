package dev.movie.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SelectedSeat {
	private Long movieId;
	private String row;
	private int col;
	private int price;
}
