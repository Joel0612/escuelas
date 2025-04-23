package com.js.escuelas.consistence.repository;

import com.js.escuelas.consistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

   Optional<UserEntity> findUserEntityByUsername(String username);
}
