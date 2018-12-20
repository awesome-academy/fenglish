package vn.framgia.dao;

import java.util.List;

import vn.framgia.model.Level;

public interface LevelDAO extends BaseDAO<Integer, Level> {
	List<Level> loadLevel(Integer offset, Integer maxResult);

	void deleteLevel(Integer key);

	List<Level> loadAllLevel();
}
