package com.freelancing.servercode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freelancing.servercode.model.RoleName;
import com.freelancing.servercode.table.Role;

@Repository
public interface RoleRepository
    extends JpaRepository<Role, Long>
{
    Optional<Role> findByName(RoleName roleName);
}