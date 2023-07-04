package com.grm.jwt.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grm.jwt.dtos.ChannelDto;
import com.grm.jwt.dtos.UserResponseDto;
import com.grm.jwt.service.ChannelService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {

	@Autowired
	private ChannelService channelService;
	
	@GetMapping("/{cId}/members")
	public ResponseEntity<List<UserResponseDto>> getAllMembers(@PathVariable Long cId){
		
		List<UserResponseDto> list = channelService.getAllMembersOfChannel(cId);
		
		return new ResponseEntity<List<UserResponseDto>>(list,HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ChannelDto> createChannel(@RequestBody ChannelDto dto,@PathParam("wId") Long wId) {
		
		ChannelDto res = channelService.createChannel(dto, wId);
		
		return new ResponseEntity<ChannelDto>(res,HttpStatus.OK);
	}
	
	@PostMapping("/{cId}/user/{uId}")
	public ResponseEntity<String> addMemberToChannel(@PathVariable Long cId, @PathVariable Long uId){
		
		String res = channelService.addMemberTOChannel(cId, uId);
		
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ChannelDto> updateChannel(@RequestBody ChannelDto dto){
		ChannelDto res = channelService.updateChannel(dto);
		
		return new ResponseEntity<ChannelDto>(res,HttpStatus.OK);
	}	
	
	@DeleteMapping("/remove/{cId}")
	public ResponseEntity<String> removeChannel(@PathVariable Long cId){
		String res = channelService.removeChannelFromWorkspace(cId);
		
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
	
	@DeleteMapping("/{cId}/user/{uId}")
	public ResponseEntity<String> removeMemberFromChannel(@PathVariable Long cId, @PathVariable Long uId){
		
		String res = channelService.removeMemberFromChannel(cId, uId);
		
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
}
