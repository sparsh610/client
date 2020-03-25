package com.freelancing.servercode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freelancing.servercode.table.Role;

@Repository
public interface RoleRepository
    extends JpaRepository<Role, Integer>
{
    Role findByRole(String role);
}
