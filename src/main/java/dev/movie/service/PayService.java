package dev.movie.service;

import dev.movie.model.dto.Payment;
import dev.service.cloud.Console;

public class PayService {
	private static int payPrice(int sale, int priceRow) {
		if (sale == 0)
			return (int) Math.round(priceRow * 0.9); // 조조 할인
		else if (sale == 1)
			return (int) Math.round(priceRow * 0.93); // 심야 할인
		return priceRow;
	}

	public static Payment payByGift(int sale, int priceRow) {
		Console.writeln("금액권의 가격을 입력하세요 💬");
		Console.write("==> ");
		int gift = Console.readInt();
		int payPrice = payPrice(sale, priceRow);
		if (payPrice >= Math.round(gift * 0.8))
			return Payment.builder().change(gift - payPrice).payType("문화상품권").price(payPrice).build();
		return null;
	}
}
