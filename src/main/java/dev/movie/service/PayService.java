package dev.movie.service;

import dev.service.cloud.Console;

public class PayService {
		
	// 문화상품권 결제 로직
	public int payByGift(int price) {
		Console.writeln("어떤 금액권을 가지고 계시나요?");
		// 금액 입력받음
		int gift = Console.readInt();
		
		// 결제 금액이 사용하려는 상품권 금액 80% 이상이여야만 사용가능
		if(price >= Math.round(gift*0.8)) {
			int change = gift - price;  
			return change;
		}
		
		return -1;
	}
}
