package vn.framgia.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import vn.framgia.bean.LevelInfo;
import vn.framgia.model.Level;

public class LevelConvertHelper {
	private static final Logger logger = Logger.getLogger(LevelConvertHelper.class);

	public static LevelInfo convertSingleLevelToLevelInfo(Level level) {

		try {
			LevelInfo levelInfo = new LevelInfo();
			BeanUtils.copyProperties(levelInfo, level);
			return levelInfo;
		} catch (Exception e) {
			logger.error("Error in convert single level to level Info: " + e.getMessage());
			return null;
		}
	}

	public static List<LevelInfo> convertLevelToLevelInfo(List<Level> Levels) {

		try {
			List<LevelInfo> LevelInfos = new ArrayList<LevelInfo>();

			for (Level Level : Levels) {
				LevelInfo LevelInfo = convertSingleLevelToLevelInfo(Level);
				LevelInfos.add(LevelInfo);
			}
			return LevelInfos;
		} catch (Exception e) {
			logger.error("Error in convert list Level to list Level Info: " + e.getMessage());
			return Collections.emptyList();
		}
	}

	public static void convertSingleLevelInfoToLevel(Level Level, LevelInfo LevelInfo) {

		try {
			BeanUtils.copyProperties(Level, LevelInfo);
		} catch (Exception e) {
			logger.error("Error in convert single Level Info to Level:" + e.getMessage());
		}
	}

	public static List<Level> convertLevelInfoToLevel(List<LevelInfo> LevelInfos) {

		try {
			List<Level> Levels = new ArrayList<Level>();

			for (LevelInfo LevelInfo : LevelInfos) {
				Level Level = new Level();
				convertSingleLevelInfoToLevel(Level, LevelInfo);
				Levels.add(Level);
			}
			return Levels;
		} catch (Exception e) {
			logger.error("Error in convert list Level Info to list Level: " + e.getMessage());
			return Collections.emptyList();
		}
	}

}
