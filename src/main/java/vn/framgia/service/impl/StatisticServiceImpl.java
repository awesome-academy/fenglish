package vn.framgia.service.impl;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import vn.framgia.service.ExerciseService;
import vn.framgia.service.StatisticService;

public class StatisticServiceImpl extends BaseServiceImpl implements StatisticService {
	@Autowired
	ExerciseService exerciseService;

	@Override
	public List<Map<Object, Object>> getChartDataExerciseLast12Month() {
		// TODO Auto-generated method stub
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		for (int i = 12; i >= 1; i--) {
			LocalDate date = LocalDate.from(LocalDate.now().minusMonths(i));
			list.add(getDataOneMonth(date.getMonthValue(), date.getYear()));
		}
		return list;
	}

	private Map<Object, Object> getDataOneMonth(int month, int year) {
		Map<Object, Object> map = null;
		map = new HashMap<Object, Object>();
		map.put("label", getMonthForInt(month)+" - " + year);
		map.put("y", exerciseService.countExerciseByMonthAndYear(month, year));
		return map;
	}

	private String getMonthForInt(int num) {
		String month = "wrong";
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
		String[] months = dateFormatSymbols.getMonths();
		if (num >= 1 && num <= 12) {
			month = months[num - 1];
		}
		return month;
	}

}
