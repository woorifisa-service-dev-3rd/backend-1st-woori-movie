package dev.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class SeatDTO {
	private int id;
	private int col;
	private int movie_id;
	private int row_id;
	
}
