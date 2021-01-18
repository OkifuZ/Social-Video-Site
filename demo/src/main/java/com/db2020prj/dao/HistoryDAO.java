package com.db2020prj.dao;

import com.db2020prj.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryDAO extends JpaRepository<History, Integer> {
    public List<History> getAllByWatcher(User watcher);

    public Integer countAllByWatcherAndWatchedBangumi(User watcher, Bangumi bangumi);

    public Integer countAllByWatcherAndWatchedColumn(User watcher, Column column);

    public Integer countAllByWatcherAndWatchedVideo(User watcher, Video video);
}
