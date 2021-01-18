package com.db2020prj.service;

import com.db2020prj.dao.FollowshipDAO;
import com.db2020prj.entities.Followship;
import com.db2020prj.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class FollowshipService {
    @Autowired
    FollowshipDAO followshipDAO;

    public void follow(User follower, User following) {
        Followship followship = new Followship();
        followship.setFollower(follower);
        followship.setFollowing(following);
        followshipDAO.save(followship);
    }

    public void unfollow(Followship followship) {
        followshipDAO.delete(followship);
    }

    public Integer getNumOfFollowers(User following) {
        List<Followship> followers = followshipDAO.getAllByFollowing(following);
        return (followers == null) ? 0 : followers.size();
    }

    public Integer getNumOfFollowings(User follower) {
        List<Followship> followed = followshipDAO.getAllByFollower(follower);
        return (followed == null) ? 0 : followed.size();
    }

    public Followship getFollowship(User follower, User following) {
        return followshipDAO.getFollowshipByFollowerAndFollowing(follower, following);
    }

}
