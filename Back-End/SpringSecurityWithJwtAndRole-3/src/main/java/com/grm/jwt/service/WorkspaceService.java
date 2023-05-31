package com.grm.jwt.service;

import java.util.List;

import com.grm.jwt.dtos.WorkspaceDto;

public interface WorkspaceService {

	WorkspaceDto createWorkspace(WorkspaceDto dto);

	String updateWorkspace(Long id,WorkspaceDto dto);
	
	List<WorkspaceDto> getAllWorkspace();
	
	List<WorkspaceDto> getAllPublicWorkspace();
}
