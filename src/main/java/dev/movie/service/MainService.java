package dev.movie.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dev.movie.model.dto.MovieTime;
import dev.movie.model.dto.Payment;
import dev.movie.model.dto.PriceDTO;
import dev.movie.model.dto.SelectedSeat;
import dev.service.cloud.Console;

public class MainService {
	private static MovieTime myMovieTime;
	private static SelectedSeat mySeat;
	
	public String getMovieList() {
		List<String> movies = MovieService.getTitles();

		while (true) {
			Console.writeln("관람하실 영화를 선택해주세요 💬");
			Console.writeln("-------------------------------------------");

			for (String movie : movies)
				Console.writeln("➡️  " + movie);

			Console.write("===> ");
			String movieName = Console.read();

			for (String movie : movies)
				if (movieName.equals(movie))
					return movieName;

			Console.writeln("해당하는 영화가 존재하지 않습니다.");
			Console.writeln();
		}
	}

	public MovieTime getTimeList(String movieName) {
		List<MovieTime> times = MovieService.getTimes(movieName);

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
				if (movieTime.equals(time.getTime())) {
					myMovieTime = MovieTime.builder().id(time.getId()).time(movieTime).build();
					return myMovieTime;
				}
			
			Console.writeln("해당하는 시간이 존재하지 않습니다.");
			Console.writeln();
		}
	}

	public SelectedSeat getSeatList(Long movieId) {
		Console.writeln("선택하신 시간의 좌석표입니다 🙋🏻‍♀️");

		while (true) {
			prtSeat(movieId);

			Console.writeln();
			SelectedSeat seatRow = selectRow();
			Console.writeln();
			int col = selectCol();

			mySeat = SelectedSeat.builder().movieId(movieId).row(seatRow.getRow()).col(col)
					.price(seatRow.getPrice()).build();
			
			Console.writeln((seatRow.getRow() + col) + " 좌석으로 결정하시겠습니까? (진행하려면 y를 입력)");
			Console.write("===> ");
			String response = Console.read();
			if(!response.equals("y")) {
				Console.writeln("좌석 결정을 다시 진행합니다.");
				Console.writeln();
				continue;
			}

			if (SeatService.isEmptySeat(movieId, col, mySeat.getRow())) return mySeat;

			Console.writeln("이미 예약이 된 좌석입니다.");
			Console.writeln();
		}
	}
	
	public Payment pay(String time, String row) {
		String[] payments = { "현금", "카드", "문화상품권" };

		while (true) {
			Console.writeln("결제수단을 골라주세요 💬");
			for (String payment : payments)
				Console.writeln("➡️  " + payment);
			
			Console.write("==> ");
			String moviePay = Console.read();
			if(moviePay.equals(payments[0]) || moviePay.equals(payments[1]) || moviePay.equals(payments[2])) {
				if(moviePay.equals(payments[0]) || moviePay.equals(payments[1])) {
					Console.writeln("결제 완료 되었습니다!");
					SeatService.saveSeat(myMovieTime.getId(), mySeat.getCol(), row);
					Console.writeln();
					prtSeat(myMovieTime.getId());
					return Payment.builder().change(-1).payType(moviePay).build();
				} else {
					int result = PayService.payByGift(chkTimeType(time), chkRowPrice(row));
					if(result != -1) {
						Console.writeln("결제 완료 되었습니다!");
						SeatService.saveSeat(myMovieTime.getId(), mySeat.getCol(), row);
						Console.writeln();
						prtSeat(myMovieTime.getId());
						return Payment.builder().change(result).payType(moviePay).build();
					}
					
					Console.writeln("문화상품권은 결제금액의 80%이상 사용하셔야만 결제가 가능합니다.");
					Console.writeln();
					continue;
				}
			}
			
			Console.writeln("존재하지 않는 결제 수단입니다.");
			Console.writeln();
		}
	}

	private static SelectedSeat selectRow() {
		List<PriceDTO> priceList = PriceService.getPrice();

		while (true) {
			Console.writeln("선택할 좌석의 행을 입력하세요 💬");
			Console.writeln("====가격표====");
			for(PriceDTO price : priceList) Console.writeln("➡️  " + price.getRow() + " ₩" + price.getPrice());
			Console.writeln("-------------------------------------------");

			Console.write("===> ");
			String seatRow = Console.read();

			for (PriceDTO price : priceList)
				if (seatRow.equals(price.getRow()))
					return SelectedSeat.builder().row(price.getRow()).price(16000).build();

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
	
	private static void prtSeat(Long movieId) {
		Console.writeln("====SCREEN====");
		Console.writeln(SeatService.getAllSeat(movieId));
	}

	private static int chkTimeType(String time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime movieTime = LocalTime.parse(time, formatter);
		LocalTime morningTime = LocalTime.parse("10:01", formatter);
		LocalTime nightTime = LocalTime.parse("20:59", formatter);

		if (movieTime.isBefore(morningTime))
			return 0;
		else if (movieTime.isAfter(nightTime))
			return 1;
		else
			return -1;
	}
	
	private static int chkRowPrice(String row) {
		switch(row) {
		case "A":
			return 16000;
		case "E":
			return 16000;
		default:
			return 18000;
		}
	}
}
