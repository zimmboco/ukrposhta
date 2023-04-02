package com.org.ukrposhta.service;

import com.org.ukrposhta.model.Level;

public interface LevelService {
    Level add(Level level);
    Level getLevelByLevelName(String levelName);
}
