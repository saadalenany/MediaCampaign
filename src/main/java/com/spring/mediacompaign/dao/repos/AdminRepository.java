package com.spring.mediacompaign.dao.repos;

import com.spring.mediacompaign.dao.entities.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, String> {

    Optional<AdminEntity> findByNameAndPassword(String name, String password);

}
