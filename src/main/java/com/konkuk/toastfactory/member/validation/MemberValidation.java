package com.konkuk.toastfactory.member.validation;

import com.konkuk.toastfactory.common.Status;
import com.konkuk.toastfactory.config.BaseException;
import com.konkuk.toastfactory.config.BaseResponseStatus;
import com.konkuk.toastfactory.member.entity.Member;
import com.konkuk.toastfactory.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberValidation {

    private final MemberRepository memberRepository;

    public void nameDuplicateValidation(String name) throws BaseException {
        Optional<Member> memberByNameAndStatus = memberRepository.findByNameAndStatus(name, Status.ACTIVE);
        if (memberByNameAndStatus.isEmpty()) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_NAME);
        }
    }
}
