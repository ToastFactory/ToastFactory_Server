package com.konkuk.toastfactory.member.service;

import com.konkuk.toastfactory.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void createMemberInDB(String name, String password) {

    }
}
