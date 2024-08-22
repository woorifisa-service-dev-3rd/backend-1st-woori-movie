package dev.movie;

import dev.movie.controller.MainController;
import dev.movie.model.dto.MovieTime;
import dev.movie.model.dto.Payment;
import dev.movie.model.dto.SelectedSeat;
import dev.service.cloud.Console;

public class Main {

	public static void main(String[] args) {
		Console.writeln("🎬 어서오세요! <우리 극장>입니다! 🎬");

		String movieName = MainController.showMovieList();
		MovieTime movieTime = MainController.showTimeList(movieName);
		SelectedSeat movieSeat = MainController.showSeatList(movieTime.getId());
		Payment pay = MainController.selectPay(movieTime.getTime(), movieSeat.getRow());
		
		Console.writeln("🎬 =< Ticket >= 🎬");
		Console.writeln("영화 제목: " + movieName);
		Console.writeln("시간: " + movieTime.getTime());
		Console.writeln("좌석: " + movieSeat.getRow() + movieSeat.getCol());
		Console.writeln("결제방식: " + pay.getPayType());
		
	}

}
