package com.grm.jwt.model;

import java.util.List;
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
	@ManyToOne(cascade = CascadeType.ALL)
	private Workspace workspace;

	// Many-to-Many: Many users can be members of the channel
	@ManyToMany
	@JoinTable(name = "channel_user", joinColumns = @JoinColumn(name = "channel_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> members;

	// One-to-Many: Channel can have multiple messages
	@OneToMany(mappedBy = "channel")
	private List<Message> messages;

	// Constructors, getters, setters
}
