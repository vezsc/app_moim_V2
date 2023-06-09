package org.edupoll.service;

import java.util.List;

import org.edupoll.model.dto.response.UserResponseData;
import org.edupoll.model.entity.Follow;
import org.edupoll.model.entity.User;
import org.edupoll.repository.FollowRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	FollowRepository followRepository;
	// 특정단어로 유저 찾아주는 기능을 처리할 서비스 구현

	public List<UserResponseData> getUsersMatchedQuery(String query) {
		// 특정단어를 포함하고 데이터를 찾아야 하는데, 기본 JpaRepository에는 없는 기능
		List<User> list = userRepository.findByIdContainingOrNickContainingAllIgnoreCase(query, query);
//		List<UserResponseData> respList = new ArrayList<>();
//		
//		for(User u : list) {
//			respList.add(new UserResponseData(u));
//		}

		return list.stream().map(t -> new UserResponseData(t)).toList();
	}

	public Object getUsersMatchedQueryBySpecificUser(String query, String logonId) {
		List<User> list = userRepository.findByIdContainingOrNickContainingAllIgnoreCase(query, query);
		
		List<UserResponseData> responseList = list.stream().map(t-> new UserResponseData(t)).toList();
		
		List<Follow> follows = followRepository.findByOwnerId(logonId);
		List<String> followedUserId = follows.stream().map(t -> t.getTarget().getId()).toList();
		
		for(UserResponseData urd : responseList) {
			if(followedUserId.contains(urd.getId())) {
				urd.setFollowed(true);
			}else {
				urd.setFollowed(false);
			}
		}
		
		
		
		return responseList;
	}
}
