package dev.movie.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RowPrice {
	private long id;
	private String row;
	private long price;
}
