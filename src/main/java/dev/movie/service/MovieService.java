package dev.movie.service;

import java.util.List;

import dev.movie.model.dao.MovieDAO;
import dev.movie.model.dto.MovieTime;

public class MovieService {
	
	public static List<String> getTitles() {
		List<String> titles = MovieDAO.findAll();
		return titles;
	}
	
	public static List<MovieTime> getTimes(String title) {
		List<MovieTime> times = MovieDAO.findAllByTitle(title);
		return times;
	}
}