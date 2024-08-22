package dev.movie.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Payment {
	private int change;
	private String payType;
}
