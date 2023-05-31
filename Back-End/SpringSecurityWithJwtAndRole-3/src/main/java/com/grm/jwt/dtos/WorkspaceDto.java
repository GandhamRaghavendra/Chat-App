package com.grm.jwt.dtos;

import com.grm.jwt.model.Workspace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkspaceDto {
	private Long id;
	private String name;
	private String desc;
	private boolean isPrivate;

	public Workspace getWorkspaceEntity(WorkspaceDto dto) {
		Workspace res = new Workspace();

		res.setName(dto.getName());
		res.setDescription(dto.getDesc());
		res.setPrivate(dto.isPrivate());
		return res;
	}

}
