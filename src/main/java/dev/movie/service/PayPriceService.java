package dev.movie.service;

public class PayPriceService {
	/**
	 * 결제금액 계산 로직
	 * @param 조조, 심야 할인 여부
	 * @param 행별 좌석 금액
	 * @return 결제해야 하는 금액
	 */
	public int payPrice(int sale, int priceRow) {
		int payPrice = priceRow;
		if( sale == 0 ) {
			// 조조할인
			payPrice = (int) Math.round(payPrice * 0.9);
		}else if( sale == 1 ) {
			// 심야할인
			payPrice = (int) Math.round(payPrice * 0.93);
		}
		return payPrice;
	}
}
