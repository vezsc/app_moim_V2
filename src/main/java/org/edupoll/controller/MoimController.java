package org.edupoll.controller;

import org.edupoll.model.dto.request.AddReplyRequestData;
import org.edupoll.model.entity.Moim;
import org.edupoll.service.AttendanceService;
import org.edupoll.service.MoimService;
import org.edupoll.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MoimController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	MoimService moimService;

	@Autowired
	ReplyService replyService;

	@Autowired
	AttendanceService attendanceService;

	@GetMapping("/moims")
	public String showMoimList(@RequestParam(defaultValue = "1") int p, Model model) {
		model.addAttribute("moims", moimService.getMoimsInSpecificPage(p));
		model.addAttribute("pagination", moimService.createPagination(p));
		return "moims/list";
	}

	@GetMapping("/moims/create")
	public String showMoimCreateForm() {
		return "moims/create";
	}

	@PostMapping("/moims/create")
	public String moimCreateHandle(Moim moim, @SessionAttribute String logonId) {
		String createdId = moimService.createNewMoim(moim, logonId);
		logger.debug("moimCreateHandle result id = {}", createdId);
		return "redirect:/moims/view?id=" + createdId;
	}

	// 특정 모임 정보 보기용 EndPoint + (리플정보도 같이)
	@GetMapping("/moims/view")
	public String showMoimDetail(String id, @RequestParam(defaultValue = "1") int p, @SessionAttribute String logonId,
			Model model) {
		model.addAttribute("moim", moimService.getSpecificMoimById(id));
		// model.addAttribute("replys", replyService.getReplysByMoimId(id, p));
		model.addAttribute("isJoined", attendanceService.isExistsAttendance(logonId, id));
		return "moims/view";
	}

	// 모임 등록 처리용 EndPoint
	@PostMapping("/moims/reply")
	public String replyAddHandle(AddReplyRequestData data) {
		replyService.createNewReplay(data);
		return "redirect:/moims/view?id=" + data.getMoimId();
	}

}
