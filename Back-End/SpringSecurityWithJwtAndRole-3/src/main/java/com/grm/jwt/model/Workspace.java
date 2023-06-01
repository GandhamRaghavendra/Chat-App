package com.grm.jwt.model;

import java.util.List;

import com.grm.jwt.dtos.WorkspaceDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
    
    private boolean isPrivate;
    
    // Relationships
    // Many-to-Many: Many users can be members of the workspace
    @ManyToMany
    @JoinTable(name = "workspace_user",
        joinColumns = @JoinColumn(name = "workspace_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> members;

    // One-to-Many: Workspace can have multiple channels
    @OneToMany(mappedBy = "workspace",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Channel> channels;

    public WorkspaceDto getWorkspaceDto(Workspace en) {
    	return WorkspaceDto.builder()
		    	                     .id(en.getId())
		    	                     .name(en.getName())
		    	                     .desc(en.getDescription())
		    	                     .isPrivate(en.isPrivate)
		    	                     .build();
    }
}
