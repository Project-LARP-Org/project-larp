package com.projectlarp.app.common.charts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChartObjectPointValue {
	private Long x;
	private Long y;
	private Long size;
}
