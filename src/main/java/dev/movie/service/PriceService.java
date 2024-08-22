package dev.movie.service;

import java.util.List;

import dev.movie.model.dao.PriceDAO;
import dev.movie.model.dto.RowPrice;

public class PriceService {
	public static List<RowPrice> getPrice() {
		return PriceDAO.getPrice();
	}
}
