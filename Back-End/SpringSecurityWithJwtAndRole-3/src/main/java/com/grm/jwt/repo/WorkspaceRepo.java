package com.grm.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grm.jwt.model.Workspace;
public interface WorkspaceRepo extends JpaRepository<Workspace, Long>{

}
