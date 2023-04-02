package com.org.ukrposhta.service.impl;

import com.org.ukrposhta.model.Level;
import com.org.ukrposhta.repository.LevelRepository;
import com.org.ukrposhta.service.LevelService;
import org.springframework.stereotype.Service;

@Service
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Level add(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public Level getLevelByLevelName(String levelName) {
        return levelRepository.findByLevelName(levelName);
    }
}
