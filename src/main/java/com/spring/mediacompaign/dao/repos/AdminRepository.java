package com.spring.mediacompaign.dao.repos;

import com.spring.mediacompaign.dao.entities.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, String> {
}
