package vn.framgia.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;

import vn.framgia.bean.LevelInfo;
import vn.framgia.helper.LevelConvertHelper;
import vn.framgia.model.Level;
import vn.framgia.service.LevelService;

public class LevelServiceImpl extends BaseServiceImpl implements LevelService {

	private static final Logger logger = Logger.getLogger(LevelServiceImpl.class);

	@Override
	public Level findById(Serializable key) {
		return levelDAO.findById(key);
	}

	@Override
	public Level saveOrUpdate(Level entity) throws IllegalAccessException, InvocationTargetException {
		logger.info("Save Or Update Level id = " + entity.getId());
		try {
			Level level = levelDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
			BeanUtils.copyProperties(level, entity);
			return levelDAO.saveOrUpdate(level);
		} catch (Exception e) {
			logger.error("Error in saveOrUpdate Level: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delete(Level entity) throws IllegalAccessException, InvocationTargetException {
		logger.info("Delete Level id = " + entity.getId());
		try {
			Level level = levelDAO.findByIdUsingLock(entity.getId(), LockMode.PESSIMISTIC_WRITE);
			BeanUtils.copyProperties(level, entity);
			levelDAO.delete(level);
			return true;
		} catch (Exception e) {
			logger.error("Error in delete Level: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<LevelInfo> loadLevels(Integer offset, Integer maxResult) {
		logger.info("Load levels");
		try {
			return LevelConvertHelper.convertLevelToLevelInfo(levelDAO.loadLevel(offset, maxResult));
		} catch (Exception e) {
			// TODO: handle exception
			return Collections.emptyList();
		}
	}

	@Override
	public boolean deleteLevelById(Integer id) {
		logger.info("Delete LevelById id = " + id);
		try {
			Level level = levelDAO.findByIdUsingLock(id, LockMode.PESSIMISTIC_WRITE);
			levelDAO.delete(level);
			return true;
		} catch (Exception e) {
			logger.error("Error in deleteLevelById: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public LevelInfo saveOrUpdateLevel(LevelInfo levelInfo) {
		logger.info("Save or Update Level");

		Level level = null;

		try {
			// Create new Level
			if (levelInfo.getId() == null) {
				level = new Level();
				LevelConvertHelper.convertSingleLevelInfoToLevel(level, levelInfo);
				level.setDeleted(false);
				return LevelConvertHelper.convertSingleLevelToLevelInfo(levelDAO.saveOrUpdate(level));
			}

			// Update Level
			level = levelDAO.findByIdUsingLock(levelInfo.getId(), LockMode.PESSIMISTIC_WRITE);
			LevelConvertHelper.convertSingleLevelInfoToLevel(level, levelInfo);
			return LevelConvertHelper.convertSingleLevelToLevelInfo(levelDAO.saveOrUpdate(level));
		} catch (Exception e) {
			logger.error("Error in saveOrUpdateLevel: " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<LevelInfo> loadAllLevel() {
		try {
			return LevelConvertHelper.convertLevelToLevelInfo(levelDAO.loadAllLevel());
		} catch (Exception e) {
			logger.error("Error in loadAllLevel: " + e.getMessage());
			return Collections.emptyList();
		}
	}

}
