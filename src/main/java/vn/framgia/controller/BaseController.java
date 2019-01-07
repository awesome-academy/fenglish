package vn.framgia.controller;

import org.springframework.beans.factory.annotation.Autowired;

import vn.framgia.helper.EmailHelper;
import vn.framgia.service.CategoryService;
import vn.framgia.service.EmailService;
import vn.framgia.service.ExerciseDetailService;
import vn.framgia.service.ExerciseService;
import vn.framgia.service.GoogleUtilsService;
import vn.framgia.service.PostService;
import vn.framgia.service.QuestionService;
import vn.framgia.service.StatisticService;
import vn.framgia.service.SubjectService;
import vn.framgia.service.UserService;

public class BaseController {
	
	@Autowired
	protected UserService userService;
	@Autowired
	protected QuestionService questionService;
	@Autowired
	protected SubjectService subjectService;
	@Autowired
	protected EmailService mailService;
	@Autowired
	protected EmailHelper emailHelper;
	@Autowired
	protected GoogleUtilsService googleUtilsService;
	@Autowired
	protected ExerciseService exerciseService;
	@Autowired
	protected ExerciseDetailService exerciseDetailService;
	@Autowired
	protected PostService postService;
	@Autowired
	protected CategoryService categoryService;
	@Autowired
	protected StatisticService statisticService;
}
