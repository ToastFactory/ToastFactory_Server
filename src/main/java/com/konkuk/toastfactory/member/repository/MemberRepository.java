package com.konkuk.toastfactory.member.repository;

import com.konkuk.toastfactory.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
