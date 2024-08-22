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
		// ticket 만들때 가져올 항목들
		// 영화 제목 = movieName
		// 상영 시간 = movieTime.getTime()
		// 좌석 = movieSeat.getRow() + movieSeat.getCol()
		// 결제방법 = pay.getPayType()
		
	}

}
