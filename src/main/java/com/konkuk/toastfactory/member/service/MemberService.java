package com.konkuk.toastfactory.member.service;

import com.konkuk.toastfactory.common.Status;
import com.konkuk.toastfactory.config.BaseException;
import com.konkuk.toastfactory.member.entity.Member;
import com.konkuk.toastfactory.member.repository.MemberRepository;
import com.konkuk.toastfactory.member.validation.MemberValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidation memberValidation;

    /**
     * 유저 이름이 중복되는지 확인 후 DB 유저 생성
     * @param name 유저 이름
     * @param password 유저 비밀번호
     * */
    public void createMemberInDB(String name, String password) throws BaseException {
        memberValidation.nameDuplicateValidation(name);
        memberRepository.save(new Member(name,password));
    }

    public void patchMemberScore(String name, Long score) {
        Optional<Member> member = memberRepository.findByNameAndStatus(name, Status.ACTIVE);
        member.get().setScore(score);
        memberRepository.save(member.get());
    }
}
