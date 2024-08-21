package dev.movie.service;

import dev.service.cloud.Console;

public class PayService {
	private static int payPrice(int sale, int priceRow) {
		int payPrice = priceRow;
		if(sale ==0) return (int) Math.round(payPrice * 0.9);
		else if(sale ==1) return (int) Math.round(payPrice * 0.93);
		return -1;
	}
	
	public int payByGift(int sale, int priceRow) {
		Console.writeln("어떤 금액권을 가지고 계시나요?");
		int gift = Console.readInt();
		int payPrice = payPrice(sale, priceRow);
		if(payPrice >= Math.round(gift*0.8)) return gift - payPrice;
		return -1;
	}
}
