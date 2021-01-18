package com.db2020prj.dao;

import com.db2020prj.entities.Followship;
import com.db2020prj.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowshipDAO extends JpaRepository<Followship, Integer> {
    List<Followship> getAllByFollower(User follower);

    List<Followship> getAllByFollowing(User following);

    Followship getFollowshipByFollowerAndFollowing(User follower, User following);


}
