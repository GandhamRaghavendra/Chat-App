package com.grm.jwt.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grm.jwt.dtos.WorkspaceDto;
import com.grm.jwt.exception.WorkspaceException;
import com.grm.jwt.model.Workspace;
import com.grm.jwt.repo.WorkspaceRepo;
import com.grm.jwt.service.WorkspaceService;

@RestController
@RequestMapping("/api/workspaces")
public class WorkspaceController {
	
    @Autowired
    private WorkspaceService workspaceService;
    
    @Autowired
    private WorkspaceRepo workspaceRepo;

	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<List<WorkspaceDto>> getAllWorkspaceDtos() {
        List<WorkspaceDto> list = workspaceService.getAllWorkspace();
        
        return new ResponseEntity<List<WorkspaceDto>>(list,HttpStatus.OK);
    }
	
	@GetMapping("/all/public")
	@PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'USER')")
    public ResponseEntity<List<WorkspaceDto>> getAllPublicWorkspaceDtos() {
        List<WorkspaceDto> list = workspaceService.getAllPublicWorkspace();
        
        return new ResponseEntity<List<WorkspaceDto>>(list,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<WorkspaceDto> getWorkspaceDtoById(@PathVariable("id") Long id) {
        Workspace res = workspaceRepo.findById(id).orElseThrow(()-> new WorkspaceException("Invalid Id"));
        
        return new ResponseEntity<WorkspaceDto>(res.getWorkspaceDto(res),HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<WorkspaceDto> createWorkspaceDto(@RequestBody WorkspaceDto workspaceDto) {
        WorkspaceDto res = workspaceService.createWorkspace(workspaceDto);
        
        return new ResponseEntity<WorkspaceDto>(res,HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<String> updateWorkspaceDto(@PathVariable("id") Long id, @RequestBody WorkspaceDto workspaceDto) {
        String res = workspaceService.updateWorkspace(id, workspaceDto);
        
        return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteWorkspaceDto(@PathVariable("id") Long id) {
    	 Workspace res = workspaceRepo.findById(id).orElseThrow(()-> new WorkspaceException("Invalid Id"));
    	 
    	 workspaceRepo.delete(res);
    	 
    	 return new ResponseEntity<String>("Done",HttpStatus.OK);
    }
}
