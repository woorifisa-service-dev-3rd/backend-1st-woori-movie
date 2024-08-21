package dev.movie;

import dev.movie.controller.SeatCtroller;

public class Main {

	public static void main(String[] args) {
		
		String test = SeatCtroller.getSeatInfo(2);
		System.out.println(test);
		boolean test2 = SeatCtroller.insertIfEmptySeat(2, 6, "A");
		System.out.println(test2);
		String test3 = SeatCtroller.getSeatInfo(2);
		System.out.println(test3);
//		String test3 = SeatCtroller.insertSeat();
	}

}
