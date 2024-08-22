package dev.movie.service;

import java.util.List;

import dev.movie.model.dao.MovieDAO;
import dev.movie.model.dto.MovieTime;
import dev.service.cloud.Console;

public class MovieService {
	
	public static String getTitles() {
		List<String> titles = MovieDAO.findAll();
		while (true) {
			Console.writeln("관람하실 영화를 선택해주세요 💬");
			Console.writeln("-------------------------------------------");

			for (String title : titles)
				Console.writeln("➡️  " + title);

			Console.write("===> ");
			String movieTitle = Console.read();

			for (String title : titles)
				if (movieTitle.equals(title))
					return movieTitle;

			Console.writeln("해당하는 영화가 존재하지 않습니다.");
			Console.writeln();
		}
	}
	
	public static MovieTime getTimes(String title) {
		List<MovieTime> times = MovieDAO.findAllByTitle(title);
		
		while (true) {
			Console.writeln("상영 시간을 선택해주세요 💬");
			Console.writeln("-------------------------------------------");
			Console.writeln("☀️ 조조 10% 할인 적용 (10:00 이전) ☀️");
			Console.writeln("🌙 심야 7% 할인 적용 (21:00 이후) 🌙");

			for (MovieTime time : times)
				Console.writeln("➡️  " + time.getTime());

			Console.write("===> ");
			String movieTime = Console.read();

			for (MovieTime time : times)
				if (movieTime.equals(time.getTime()))
					return MovieTime.builder().id(time.getId()).time(movieTime).build();
			
			Console.writeln("해당하는 시간이 존재하지 않습니다.");
			Console.writeln();
		}
	}
}