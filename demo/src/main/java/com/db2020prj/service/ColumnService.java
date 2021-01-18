package com.db2020prj.service;

import com.db2020prj.controllers.request.PostColumnRequest;
import com.db2020prj.dao.ColumnDAO;
import com.db2020prj.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnService {
    @Autowired
    ColumnDAO columnDAO;
    @Autowired
    TagService tagService;
    @Autowired
    TagRelationService tagRelationService;

    public List<Column> findAll() {
        return columnDAO.findAll();
    }

    public List<Column> findAllByNameLike(String keyword) {
        return columnDAO.findByColumnNameLike(keyword);
    }

    public void addColumn(PostColumnRequest request, User uploader, Image coverImage) {
        Column newColumn = new Column();
        newColumn.setColumnName(request.getColumnTitle());
        newColumn.setColumnReadTimes(0);
        newColumn.setColumnContent(request.getColumnText());
        newColumn.setColumnUploader(uploader);
        newColumn.setColumnImage(coverImage);
        newColumn.setColumnLikeNumbers(0);
        columnDAO.save(newColumn);
        List<String> tagNames = request.getNewColumnTags();
        for (String tagName: tagNames) {
            Tag theTag = tagService.createOrGetTag(tagName);
            tagRelationService.addRelation(theTag, newColumn);
        }

    }

    public Column getColumnById(int id) {
        return columnDAO.findById(id).orElse(null);
    }

    public List<Column> findAllByColumnUploader(User uploader) {
        return columnDAO.findAllByColumnUploader(uploader);
    }

    public void deleteColumn(Column column) {
        columnDAO.delete(column);
    }
}
