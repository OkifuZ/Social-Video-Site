package com.db2020prj.service;

import com.db2020prj.controllers.request.PostVideoRequest;
import com.db2020prj.dao.VideoDAO;
import com.db2020prj.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    @Autowired
    VideoDAO videoDAO;
    @Autowired
    TagService tagService;
    @Autowired
    TagRelationService tagRelationService;

    public List<Video> findAll() {
        return videoDAO.findAll();
    }

    public List<Video> findAllByNameLike(String keyword) {
        return videoDAO.findByVideoNameLike(keyword);
    }

    public Video getVideoById(int id) { return videoDAO.findById(id).orElse(null); }

    public void addVideo(PostVideoRequest request, User uploader, Image image) {
        Video video = new Video();
        video.setVideoContentUrl(request.getVideoUrl());
        video.setVideoDescription(request.getVideoDes());
        video.setVideoDuration("0.00");
        video.setVideoImage(image);
        video.setVideoName(request.getVideoTitle());
        video.setVideoPlayTimes(0);
        video.setVideoUploader(uploader);
        video.setVideoLikeNumbers(0);
        videoDAO.save(video);
        List<String> tags = request.getNewVideoTags();
        for (String tagName: tags) {
            Tag theTag = tagService.createOrGetTag(tagName);
            tagRelationService.addRelation(theTag, video);
        }
    }

    public List<Video> findAllByVideoUploader(User uploader) {
        return videoDAO.findAllByVideoUploader(uploader);
    }

    public void deleteVideo(Video video) {
        videoDAO.delete(video);
    }
}
