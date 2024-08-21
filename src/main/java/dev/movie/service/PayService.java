package dev.movie.service;

import dev.service.cloud.Console;

public class PayService {
	public int payByGift(int price) {
		Console.writeln("어떤 금액권을 가지고 계시나요?");
		int gift = Console.readInt();
		if(price >= Math.round(gift*0.8)) return gift - price;
		return -1;
	}
}
