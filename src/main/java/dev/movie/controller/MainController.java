package dev.movie.controller;

import java.util.List;

import dev.movie.model.dto.MovieTime;
import dev.movie.model.dto.PriceDTO;
import dev.movie.model.dto.SelectedSeat;
import dev.movie.service.MainService;
import dev.movie.service.MovieService;
import dev.service.cloud.Console;

public class MainController {
	private static MainService mainService = new MainService();

	public static String showMovieList() {
		Console.writeln();
		return mainService.getMovieList();
	}
	
	public static Long showTimeList(String movieName) {
		Console.writeln();
		return mainService.getTimeList(movieName);
	}
	
	public static SelectedSeat showSeatList(Long movieId) {
		Console.writeln();
		return mainService.getSeatList(movieId);
	}
	
	public static List<PriceDTO>showPrice() {
		Console.writeln();
		return mainService.getPrice(); 
	}
	
	public static List<String> getTitles() {
		Console.writeln();
		return MovieService.getTitles();
	}
	
	public static List<MovieTime> getTimes(String title) {
		Console.writeln();
		return MovieService.getTimes(title);
	}
}
