package vn.framgia.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.framgia.bean.LevelInfo;
import vn.framgia.controller.BaseController;

@Controller
@RequestMapping("/admin/levels")
public class LevelController extends BaseController {
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<LevelInfo> loadAllLevel() {
		return levelService.loadAllLevel();
	}
	
}
