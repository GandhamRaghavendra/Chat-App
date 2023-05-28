package com.grm.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grm.jwt.model.Channel;
public interface ChannelRepo extends JpaRepository<Channel, Long>{

}
