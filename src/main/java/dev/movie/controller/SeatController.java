package dev.movie.controller;

import java.util.ArrayList;
import java.util.List;

import dev.movie.model.dto.SeatDTO;
import dev.movie.service.SeatService;

public class SeatController {
	public static String getSeatInfo(int movie_id) {
		List<SeatDTO> seatDtos = new ArrayList<>();
		
		String str = SeatService.findAllSeat(movie_id);
		
		
		return str;
	}
	
	public static boolean insertIfEmptySeat (int movie_id, int col, String row) {
		
		boolean isSuccess = SeatService.insertIfEmptySeat(movie_id, col, row);
		return isSuccess;
	}
}
