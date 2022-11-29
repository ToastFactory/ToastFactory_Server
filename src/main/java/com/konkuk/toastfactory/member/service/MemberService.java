package com.konkuk.toastfactory.member.service;

import com.konkuk.toastfactory.config.BaseException;
import com.konkuk.toastfactory.member.entity.Member;
import com.konkuk.toastfactory.member.repository.MemberRepository;
import com.konkuk.toastfactory.member.validation.MemberValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidation memberValidation;

    public void createMemberInDB(String name, String password) throws BaseException {
        memberValidation.nameDuplicateValidation(name);
        memberRepository.save(new Member(name,password));
    }
}
