package dev.movie.service;

import dev.movie.model.dto.SelectedSeat;
import dev.service.cloud.Console;

public class MainService {
	public String getMovieList() {
		// showMovie();
		String[] movies = { "파일럿", "데드풀", "인사이드아웃" };

		while (true) {
			Console.writeln("관람하실 영화를 선택해주세요 💬");
			Console.writeln("-------------------------------------------");

			for (String movie : movies)
				Console.writeln("➡️ " + movie);

			Console.write("===> ");
			String movieName = Console.read();

			for (String movie : movies)
				if (movieName.equals(movie))
					return movieName;

			Console.writeln("해당하는 영화가 존재하지 않습니다.");
			Console.writeln();
		}
	}

	public Long getTimeList(String movieName) {
		// showTimes();
		String[] times = { "10:00", "13:00", "15:00" };

		while (true) {
			Console.writeln("상영 시간을 선택해주세요 💬");
			Console.writeln("-------------------------------------------");
			Console.writeln("☀️ 조조 10% 할인 적용 (10:00 이전) ☀️");
			Console.writeln("🌙 심야 7% 할인 적용 (21:00 이후) 🌙");

			for (String time : times)
				Console.writeln("➡️ " + time);

			Console.write("===> ");
			String movieTime = Console.read();

			for (String time : times)
				if (movieTime.equals(time))
					return 1L;

			Console.writeln("해당하는 시간이 존재하지 않습니다.");
			Console.writeln();
		}
	}

	public SelectedSeat getSeatList(Long movieId) {
		String seats = SeatService.getAllSeat(movieId);
		Console.writeln("선택하신 시간의 좌석표입니다 🙋🏻‍♀️");

		while (true) {
			Console.writeln("====SCREEN====");
			Console.writeln(seats);

			Console.writeln();
			SelectedSeat seatRow = selectRow();
			Console.writeln();
			int col = selectCol();

			SelectedSeat movieSeat = SelectedSeat.builder().movieId(movieId).row(seatRow.getRow()).col(col)
					.price(seatRow.getPrice()).build();
			if (SeatService.insertIfEmptySeat(movieId, col, seats))
				return movieSeat;
			Console.writeln("이미 예약이 된 좌석입니다.");
		}
	}

	private static SelectedSeat selectRow() {
		String[] rows = { "A", "B", "C", "D", "E" };
		// 가격표 가져와서 아래서 출력

		while (true) {
			Console.writeln("A ~ E 중 선택할 좌석의 행을 입력하세요 💬");
			Console.writeln("-------------------------------------------");

			Console.write("===> ");
			String seatRow = Console.read();

			for (String row : rows)
				if (seatRow.equals(row))
					return SelectedSeat.builder().row(row).price(16000).build();

			Console.writeln("존재하지 않는 행입니다.");
			Console.writeln();
		}
	}

	private static int selectCol() {
		int minCol = 1;
		int maxCol = 6;

		while (true) {
			Console.writeln("선택할 좌석의 열을 입력하세요 💬");
			Console.writeln("-------------------------------------------");

			Console.write("===> ");
			int seatCol = Console.readInt();

			if (seatCol >= minCol && seatCol <= maxCol)
				return seatCol;

			Console.writeln("존재하지 않는 열입니다.");
			Console.writeln();
		}
	}
}
