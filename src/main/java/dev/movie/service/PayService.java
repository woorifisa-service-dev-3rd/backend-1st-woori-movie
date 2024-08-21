package dev.movie.service;

import dev.service.cloud.Console;

public class PayService {
	private static int payPrice(int sale, int priceRow) {
		if(sale ==0) return (int) Math.round(priceRow * 0.9); // 조조 할인
		else if(sale ==1) return (int) Math.round(priceRow * 0.93); // 심야 할인
		return -1;
	}
	
	public int payByGift(int sale, int priceRow) {
		Console.writeln("어떤 금액권을 가지고 계시나요?");
		int gift = Console.readInt();
		if(price >= Math.round(gift*0.8)) return gift - payPrice(sale, priceRow);
		return -1;
	}
}
