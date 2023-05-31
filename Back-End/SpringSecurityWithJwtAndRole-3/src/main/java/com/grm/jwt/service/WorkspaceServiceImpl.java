package com.grm.jwt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grm.jwt.dtos.WorkspaceDto;
import com.grm.jwt.exception.WorkspaceException;
import com.grm.jwt.model.Workspace;
import com.grm.jwt.repo.WorkspaceRepo;

@Service
public class WorkspaceServiceImpl implements WorkspaceService{

	@Autowired
	private WorkspaceRepo workspaceRepo;

	@Override
	public WorkspaceDto createWorkspace(WorkspaceDto dto) {
		Workspace workspaceEntity = dto.getWorkspaceEntity(dto);
		
		Workspace savedEntity = workspaceRepo.save(workspaceEntity);
		
		dto.setId(savedEntity.getId());
		
		return dto;
	}

	@Override
	public String updateWorkspace(Long id,WorkspaceDto dto) {
		
		Workspace entity = workspaceRepo.findById(id).orElseThrow(()-> new WorkspaceException("Invalid Id"));
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDesc());
		
		workspaceRepo.save(entity);
		
		return "updated done";
		
	}

	@Override
	public List<WorkspaceDto> getAllWorkspace() {
	    List<Workspace> list = workspaceRepo.findAll();
	    
	    return list.stream().map(ent -> ent.getWorkspaceDto(ent)).collect(Collectors.toList());
	}

	@Override
	public List<WorkspaceDto> getAllPublicWorkspace() {
		List<Workspace> list = workspaceRepo.findAll();
		
		return list.stream()
				           .filter(w -> !w.isPrivate())
				           .map(w -> w.getWorkspaceDto(w))
				           .collect(Collectors.toList());
	}
}
