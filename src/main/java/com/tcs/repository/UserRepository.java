package com.tcs.repository;

import java.util.Optional;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.entity.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
	Optional<UserAccount> findByEmail(String email);
}
