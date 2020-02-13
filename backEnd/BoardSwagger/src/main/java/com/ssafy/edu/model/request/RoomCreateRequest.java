package com.ssafy.edu.model.request;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomCreateRequest {
	private String roomName;
	private String hostId;
	private List<String> guestId;
}
