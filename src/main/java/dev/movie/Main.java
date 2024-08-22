package dev.movie;

import dev.movie.controller.MainController;
import dev.movie.util.DBUtil;
import dev.service.cloud.Console;

public class Main {

	public static void main(String[] args) {
		DBUtil.setArgument(args[0]);
		Console.writeln("🎬 어서오세요! <우리 극장>입니다! 🎬");

		MainController.showMovieList();
	    MainController.showTimeList();
		MainController.showSeatList();
		MainController.selectPay();
		MainController.printTicket();
	}

}
