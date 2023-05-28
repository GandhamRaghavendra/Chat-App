package com.grm.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grm.jwt.model.Message;
public interface MessageRepo extends JpaRepository<Message, Long>{

}
