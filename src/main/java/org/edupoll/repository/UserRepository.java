package org.edupoll.repository;

import java.util.List;

import org.edupoll.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

	List<User> findByIdContainingOrNickContainingAllIgnoreCase(String id, String nick);
}
