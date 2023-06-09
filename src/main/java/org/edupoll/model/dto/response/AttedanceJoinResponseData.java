package org.edupoll.model.dto.response;

import java.util.List;

public class AttedanceJoinResponseData {

	Boolean result;
	String errorMessage;

	Integer currentPerson;

	List<String> attendUserIds;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getCurrentPerson() {
		return currentPerson;
	}

	public void setCurrentPerson(Integer currentPerson) {
		this.currentPerson = currentPerson;
	}

	public List<String> getAttendUserIds() {
		return attendUserIds;
	}

	public void setAttendUserIds(List<String> attendUserIds) {
		this.attendUserIds = attendUserIds;
	}

}
