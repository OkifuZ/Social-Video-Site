package com.db2020prj.service;

import com.db2020prj.dao.BangumiDAO;
import com.db2020prj.dao.ColumnDAO;
import com.db2020prj.dao.VideoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentService {
    @Autowired
    BangumiDAO bangumiDAO;
    @Autowired
    VideoDAO videoDAO;
    @Autowired
    ColumnDAO columnDAO;
}
