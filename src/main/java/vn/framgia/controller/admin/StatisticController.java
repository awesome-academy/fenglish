package vn.framgia.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.framgia.controller.BaseController;

@Controller
@RequestMapping("/admin/statistic")
public class StatisticController extends BaseController {

	@RequestMapping(value = "/exerciseChart", method = RequestMethod.GET)
	public String showChart(HttpSession session, Model model) {
		return "/statistic/exercisesChart";
	}

	// get chart data last 12 month
	@GetMapping("/exercises")
	@ResponseBody
	public List<Map<Object, Object>> getExerciseChartData() {
		return statisticService.getChartDataExerciseLast12Month();
	}

}
