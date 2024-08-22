package dev.movie.controller;

import dev.movie.model.dto.MovieTime;
import dev.movie.model.dto.Payment;
import dev.movie.model.dto.SelectedSeatDTO;
import dev.movie.service.MovieService;
import dev.movie.service.PayService;
import dev.movie.service.SeatService;
import dev.service.cloud.Console;

public class MainController {
	private static String movieName;
	private static MovieTime userMovieTime;
	private static SelectedSeatDTO userSeat;
	private static Payment userPay;
	
	public static String showMovieList() {
		Console.writeln();
		movieName = MovieService.getTitles();
		return movieName;
	}

	public static MovieTime showTimeList() {
		Console.writeln();
		userMovieTime = MovieService.getTimes(movieName);
		return userMovieTime;
	}

	public static SelectedSeatDTO showSeatList() {
		Console.writeln();
		userSeat = SeatService.getSeatList(userMovieTime.getId());
		return userSeat;
	}
	
	public static Payment selectPay() {
		Console.writeln();
		userPay = PayService.pay(userMovieTime, userSeat);
		return userPay;
	}
	
	public static void printTicket() {
		Console.writeln("🎬 =< Ticket >= 🎬");
		Console.writeln("영화 제목: " + movieName);
		Console.writeln("시간: " + userMovieTime.getTime());
		Console.writeln("좌석: " + userSeat.getRow() + userSeat.getCol());
		Console.writeln("결제 방식: " + userPay.getPayType());
		Console.writeln("결제 금액: " + userPay.getPrice());
	}

}
