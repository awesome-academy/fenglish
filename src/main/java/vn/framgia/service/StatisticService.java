package vn.framgia.service;

import java.util.List;
import java.util.Map;

public interface StatisticService {
	List<Map<Object,Object>> getChartDataExerciseLast12Month();
}
