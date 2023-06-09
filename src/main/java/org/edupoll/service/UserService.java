package org.edupoll.service;

import java.util.Optional;

import org.edupoll.model.dto.request.LoginRequestData;
import org.edupoll.model.entity.User;
import org.edupoll.model.entity.UserDetail;
import org.edupoll.repository.UserDetailRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailRepository userDetailRepository;

	@Transactional
	public boolean deleteSpecificUser(String userId) {
		// userId 에 해당하는 데이터를 userRepository 에서 지우고
		if (!userRepository.findById(userId).isPresent()) {
			return false;
		}

		User found = userRepository.findById(userId).get();
		// 멀 먼저 지워? UserDetail ?? User ??
		userRepository.delete(found);
		userDetailRepository.delete(found.getUserDetail());
		return true;
	}

	// 회원상세정보를 수정/변경 처리할 서비스 메서드
	public boolean modifySpecificUserDetail(String userId, UserDetail detail) {
		// 1.특정 유저가 존재하는지 확인
		if (userRepository.findById(userId).isEmpty())
			return false;

		// 2.UserDeatil 저장하고
		User foundUser = userRepository.findById(userId).get();
		if (foundUser.getUserDetail() != null)
			detail.setIdx(foundUser.getUserDetail().getIdx());

		UserDetail saved = userDetailRepository.save(detail);
		// 3.특정 유저의 detail_idx 에 방금 지정하며 부여받은 id 값을 설정해서 update
		foundUser.setUserDetail(saved);
		userRepository.save(foundUser);

		return true;
	}

	// 회원가입을 처리할 서비스 메서드
	public boolean createNewUser(User user) {
		if (userRepository.findById(user.getId()).isEmpty()) {
			// user.setJoinDate(new Date());

			userRepository.save(user);
			return true;
		}
		return false;
	}

	// 로그인 처리를 하기 위해 사용할 서비스 메서드
	public boolean isValidUser(LoginRequestData data) { // id, pass
		Optional<User> optional = userRepository.findById(data.getLoginId());
		if (optional.isPresent()) {
			String savedPass = optional.get().getPass();
			return savedPass.equals(data.getLoginPass());
		}
		return false;
	}

	public UserDetail findSpecificSavedDetail(String logonId) {

		return userRepository.findById(logonId).get().getUserDetail();
	}

	public User findSpecificUserById(String targetId) {
		return userRepository.findById(targetId).orElse(null);
	}
}
