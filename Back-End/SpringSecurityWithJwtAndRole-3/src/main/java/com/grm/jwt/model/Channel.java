package com.grm.jwt.model;

import java.util.List;

import com.grm.jwt.dtos.ChannelDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Channel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;
	
	private boolean isPrivate;

	// Additional channel attributes

	// Relationships
	// Many-to-One: Channel belongs to a workspace
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private Workspace workspace;

	// Many-to-Many: Many users can be members of the channel
	@ManyToMany
	@JoinTable(name = "channel_user", joinColumns = @JoinColumn(name = "channel_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> members;

	// One-to-Many: Channel can have multiple messages
	@OneToMany(mappedBy = "channel")
	private List<Message> messages;

    public ChannelDto getChannelDtoByEntity(Channel channel) {
        ChannelDto dto = ChannelDto.builder()
                           .id(channel.getId())
                           .name(channel.getName())
                           .description(channel.getDescription())
                           .isPrivate(channel.isPrivate())
                           .build();
       return dto;      
    }
}
