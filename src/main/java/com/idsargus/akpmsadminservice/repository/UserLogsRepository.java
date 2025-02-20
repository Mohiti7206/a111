package com.idsargus.akpmsadminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idsargus.akpmsadminservice.entity.UserLogsEntity;

@Repository
public interface UserLogsRepository  extends JpaRepository<UserLogsEntity, Integer>{

}
