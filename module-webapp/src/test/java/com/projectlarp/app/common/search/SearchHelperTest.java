package com.projectlarp.app.common.search;

import static com.projectlarp.app.common.search.SearchHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.projectlarp.app.common.search.SearchHelper;

public class SearchHelperTest {

	@Test
	public void test_isBlank() {
		assertThat(isBlank(null)).isTrue();
		assertThat(isBlank("")).isTrue();
		assertThat(isBlank("  ")).isTrue();
		assertThat(isBlank("TEST")).isFalse();
		assertThat(isBlank(" TEST ")).isFalse();
		//
		assertThat(isBlank("%%")).isTrue();
		assertThat(isBlank("%  %")).isTrue();
		assertThat(isBlank("%TEST%")).isFalse();
		assertThat(isBlank("% TEST %")).isFalse();
	}

	@Test
	public void test_areBlank() {
		assertThat(areBlank(null, null)).isTrue();
		assertThat(areBlank("", "")).isTrue();
		assertThat(areBlank("  ", "  ")).isTrue();
		assertThat(areBlank("%TEST%", "")).isFalse();
		assertThat(areBlank("", "%TEST%")).isFalse();
		assertThat(areBlank("%TEST% ", " %TEST% ")).isFalse();
	}

	@Test
	public void test_areNotBlank() {
		assertThat(areNotBlank(null, null)).isFalse();
		assertThat(areNotBlank("", "")).isFalse();
		assertThat(areNotBlank("  ", "  ")).isFalse();
		assertThat(areNotBlank("%TEST%", "")).isFalse();
		assertThat(areNotBlank("", "%TEST%")).isFalse();
		assertThat(areNotBlank("%TEST% ", " %TEST% ")).isTrue();
	}

	@Test
	public void test_date_format() {
		assertThat(SearchHelper.timestamp("2016-08-16T13:03:09.552Z")) //
		.isWithinYear(2016)
		.isWithinMonth(8)
		.isWithinDayOfMonth(16)
		.isWithinHourOfDay(13)
		.isWithinMinute(3)
		.isWithinSecond(9)
		.isWithinMillisecond(552);
	}
}