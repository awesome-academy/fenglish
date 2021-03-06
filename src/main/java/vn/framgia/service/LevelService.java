package vn.framgia.service;

import java.util.List;
import java.util.Map;

import vn.framgia.bean.LevelInfo;
import vn.framgia.model.Level;

public interface LevelService extends BaseService<Integer, Level> {

	List<LevelInfo> loadLevels(Integer offset, Integer maxResult);

	boolean deleteLevelById(Integer id);

	LevelInfo saveOrUpdateLevel(LevelInfo LevelInfo);

	List<LevelInfo> loadAllLevel();

	Map<Integer, Level> loadMapLevel();
}
