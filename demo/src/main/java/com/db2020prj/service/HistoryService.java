package com.db2020prj.service;

import com.db2020prj.dao.BangumiDAO;
import com.db2020prj.dao.ColumnDAO;
import com.db2020prj.dao.HistoryDAO;
import com.db2020prj.dao.VideoDAO;
import com.db2020prj.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    HistoryDAO historyDAO;
    @Autowired
    VideoDAO videoDAO;
    @Autowired
    ColumnDAO columnDAO;
    @Autowired
    BangumiDAO bangumiDAO;

    public void watchContent(User watcher, Object content) {
        History history = new History();
        history.setWatcher(watcher);
        int watchedHistory;
        if (Bangumi.class.equals(content.getClass())) {
            Bangumi bangumi = (Bangumi) content;
            watchedHistory = historyDAO.countAllByWatcherAndWatchedBangumi(watcher, bangumi);
            if (watchedHistory == 0) {
                bangumi.setBangumiPlayTimes(bangumi.getBangumiPlayTimes() + 1);
                bangumiDAO.save(bangumi);
            }
            history.setWatchedType(ReferenceContentType.BANGUMI);
            history.setWatchedBangumi(bangumi);
        } else if (Video.class.equals(content.getClass())) {
            Video video = (Video) content;
            watchedHistory = historyDAO.countAllByWatcherAndWatchedVideo(watcher, video);
            if (watchedHistory == 0) {
                video.setVideoPlayTimes(video.getVideoPlayTimes() + 1);
                videoDAO.save(video);
            }
            history.setWatchedType(ReferenceContentType.VIDEO);
            history.setWatchedVideo(video);
        } else if (Column.class.equals(content.getClass())) {
            Column column = (Column) content;
            watchedHistory = historyDAO.countAllByWatcherAndWatchedColumn(watcher, column);
            if (watchedHistory == 0) {
                column.setColumnReadTimes(column.getColumnReadTimes() + 1);
                columnDAO.save(column);
            }
            history.setWatchedType(ReferenceContentType.COLUMN);
            history.setWatchedColumn(column);
        }
        historyDAO.save(history);
    }

    public List<History> getUserHistory(User user) {
        return historyDAO.getAllByWatcher(user);
    }
}
