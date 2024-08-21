package dev.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public class SeatDTO {
	private int id;
	private int col;
	private int movieId;
	private int rowId;
	
}
