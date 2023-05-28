package com.grm.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grm.jwt.model.Attachment;
public interface AttachmentRepo extends JpaRepository<Attachment, Long>{

}
