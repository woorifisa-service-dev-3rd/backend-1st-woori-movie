package dev.movie.controller;

import java.util.List;

import dev.movie.model.dto.MovieTime;
import dev.movie.service.MovieService;

public class MovieController {
	
	
	public static List<String> getTitles() {
		List<String> titles = MovieService.getTitles();
		return titles;
	}
	
	public static List<MovieTime> getTimes(String title) {
		List<MovieTime> times = MovieService.getTimes(title);
		return times;
	}
}
