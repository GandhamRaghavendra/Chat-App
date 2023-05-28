package com.grm.jwt.model;

import java.time.LocalDateTime;
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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime timestamp;

    // Additional message attributes

    // Relationships
    // Many-to-One: Message belongs to a channel
    @ManyToOne
    private Channel channel;

    // Many-to-One: Message is sent by a user
    @ManyToOne
    private User sender;

    // One-to-Many: Message can have multiple attachments
    @OneToMany(mappedBy = "message")
    private List<Attachment> attachments;

    // Constructors, getters, setters
}
