package com.konkuk.toastfactory.rank.validation;

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
public class RankValidation {

    private final MemberRepository memberRepository;

    public void memberExistValidation(String name) throws BaseException {
        Optional<Member> member = memberRepository.findByNameAndStatus(name, Status.ACTIVE);
        if (member.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_MEMBER);
        }
    }

    public void memberScoreExistValidation(String name) throws BaseException {
        Optional<Member> member = memberRepository.findByNameAndStatus(name, Status.ACTIVE);
        if (member.get().getScore().equals(null)) {
            throw new BaseException(BaseResponseStatus.NOT_YET_START);
        }
    }

}
