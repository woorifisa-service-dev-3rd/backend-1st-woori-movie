package dev.movie.service;

import java.util.Iterator;
import java.util.List;

import dev.movie.model.dao.SeatDAO;
import dev.movie.model.dto.SeatDTO;

public class SeatService {
	public static String findAllSeat(int movie_id) {
		int[] row = {1, 2, 3, 4, 5};
		List<SeatDTO> seatDtos = SeatDAO.findAllSeat(movie_id);
		StringBuilder sb = new StringBuilder();
		
		for(int r : row) {
			String[] col = {" 1", " 2", " 3", " 4", " 5", " 6"};
			sb.append((char)(r + 64) +  "|");
			Iterator<SeatDTO> iter = seatDtos.iterator();
			
			while(iter.hasNext()) {
				SeatDTO seat = (SeatDTO) iter.next();
				if(r == seat.getRow_id()) {
					col[seat.getCol() - 1] = " X";
				}
			}
			for(String i : col) {
				sb.append(i);
			}
			if(r != 5) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	public static boolean insertIfEmptySeat(int movie_id, int col, String row) {
		
		boolean isEmpty = SeatDAO.findSeat(movie_id, col, row);
		
		if(isEmpty) {
			boolean isSuccess = SeatDAO.insertSeat(movie_id, col, row);
			return isSuccess;
			
		} else {
			return false;
		}
		
	}
}
