package com.binary_studio.academy_coin_test;

import java.util.Arrays;

import com.binary_studio.academy_coin.AcademyCoin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AcademyCoinTest {

	public static final String TASK = "academy_coin";

	@Test
	@DisplayName("Two Spikes")
	void AcademyCoin_ProfitWithTwoSpikes_SpikeDiffSumReturned() {
		assertEquals(7, AcademyCoin.maxProfit(Arrays.stream(new int[] { 7, 1, 5, 3, 6, 4 }).boxed()),
				"Should be 7 for two spikes: buy on day 2 - sell on day 3(5-1 = 4), buy on day 4 - sell on day 5(6 - 3 = 3), equals to 7(4 + 3) in total profit");
	}

	@Test
	@DisplayName("No fall")
	void AcademyCoin_ProfitWithNoDecrease_FirstAndLastDiffReturned() {
		assertEquals(4, AcademyCoin.maxProfit(Arrays.stream(new int[] { 1, 2, 3, 4, 5 }).boxed()),
				"Should be 4: buy on day 1, sell on day 5 for 5-1 = 4. Notice, that you can't engage in multiple transactions");
	}

	@Test
	@DisplayName("No profit")
	void AcademyCoin_NoProfitAtAll_ZeroReturned() {
		assertEquals(0, AcademyCoin.maxProfit(Arrays.stream(new int[] { 7, 6, 4, 3, 1 }).boxed()),
				"There is no profit to be made, so return zero");
	}

}
