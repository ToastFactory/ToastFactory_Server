package com.konkuk.toastfactory.member.repository;

import com.konkuk.toastfactory.common.Status;
import com.konkuk.toastfactory.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByNameAndStatus(String name, Status status);
}
