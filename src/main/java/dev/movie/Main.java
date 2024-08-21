package dev.movie;

import dev.movie.controller.MainController;
import dev.movie.model.dto.SelectedSeat;
import dev.service.cloud.Console;

public class Main {

	public static void main(String[] args) {
		Console.writeln("🎬 어서오세요! <우리 극장>입니다! 🎬");

		String movieName = MainController.showMovieList();
		Long movieId = MainController.showTimeList(movieName);
		SelectedSeat movieSeat = MainController.showSeatList(movieId);
		
	}

}
