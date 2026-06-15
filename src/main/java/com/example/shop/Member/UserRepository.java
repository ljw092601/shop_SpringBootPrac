package com.example.shop.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, Integer> {

    Member findByUsername(String username);
}
