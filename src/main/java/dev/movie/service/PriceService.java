package dev.movie.service;

import java.util.List;

import dev.movie.model.dao.PriceDAO;
import dev.movie.model.dto.PriceDTO;

public class PriceService {
	public static List<PriceDTO> getPrice() {
		return PriceDAO.getPrice();
	}
}
