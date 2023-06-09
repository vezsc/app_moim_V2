package org.edupoll.controller.api;

import org.edupoll.model.dto.response.FollowResponseData;
import org.edupoll.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
public class FollowController {

	@Autowired
	FollowService followService;

	@PostMapping("/api/follow")
	public FollowResponseData followPostHandle(@SessionAttribute(name = "logonId") String owner,
			@RequestParam String target) {
		return followService.createFollow(owner, target);
	}
	
	@DeleteMapping("/api/follow")
	public FollowResponseData followDeleteHandle(@SessionAttribute(name = "logonId") String owner,
			@RequestParam String target) {
		return followService.deleteFollow(owner, target);
	}
}
