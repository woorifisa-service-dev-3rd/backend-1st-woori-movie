package dev.movie.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import dev.movie.model.dto.MovieTime;
import dev.movie.model.dto.Payment;
import dev.movie.model.dto.SelectedSeatDTO;
import dev.service.cloud.Console;

public class PayService {
	public static Payment pay(MovieTime movieTime, SelectedSeatDTO seat) {
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
					SeatService.saveSeat(movieTime.getId(), seat.getCol(), seat.getRow());
					Console.writeln();
					return Payment.builder().change(-1).payType(moviePay).build();
				} else {
					Payment myPayment = payByGift(chkTimeType(movieTime.getTime()), chkRowPrice(seat.getRow()));
					if(myPayment != null) {
						if(myPayment.getChange() < 0) {
							Console.writeln("결제 금액이 문화상품권 금액보다 높을 수 없습니다.");
							Console.writeln();
							continue;
						}
						
						Console.writeln("결제 완료 되었습니다!");
						SeatService.saveSeat(movieTime.getId(), seat.getCol(), seat.getRow());
						Console.writeln();
						return myPayment;
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
	
	private static int payPrice(int sale, int priceRow) {
		if (sale == 0)
			return (int) Math.round(priceRow * 0.9);
		else if (sale == 1)
			return (int) Math.round(priceRow * 0.93);
		return priceRow;
	}

	private static Payment payByGift(int sale, int priceRow) {
		Console.writeln("금액권의 가격을 입력하세요 💬");
		Console.write("==> ");
		int gift = Console.readInt();
		int payPrice = payPrice(sale, priceRow);
		if (payPrice >= Math.round(gift * 0.8))
			return Payment.builder().change(gift - payPrice).payType("문화상품권").price(payPrice).build();
		return null;
	}
}
