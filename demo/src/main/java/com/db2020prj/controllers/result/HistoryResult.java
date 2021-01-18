package com.db2020prj.controllers.result;

import com.db2020prj.entities.History;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HistoryResult {
    @JsonProperty
    List<BangumiResult> bangumis;
    @JsonProperty
    List<ColumnResult> columns;
    @JsonProperty
    List<VideoResult> videos;

    public HistoryResult(List<History> histories) {
        this.bangumis = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.videos = new ArrayList<>();
        histories.sort(new Comparator<History>() {
            @Override
            public int compare(History history, History t1) {
                return t1.getWatchDate().compareTo(history.getWatchDate());
            }
        });
        for (History history: histories) {
            switch (history.getWatchedType()) {
                case VIDEO:
                    videos.add(new VideoResult(history.getWatchedVideo()));
                    break;
                case COLUMN:
                    columns.add(new ColumnResult(history.getWatchedColumn()));
                    break;
                case BANGUMI:
                    bangumis.add(new BangumiResult(history.getWatchedBangumi()));
                    break;
            }
        }
    }
}
