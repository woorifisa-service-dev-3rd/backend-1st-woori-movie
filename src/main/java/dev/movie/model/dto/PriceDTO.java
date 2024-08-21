package dev.movie.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PriceDTO {
	private long id;
	private long price;
}
