package com.konkuk.toastfactory.member.controller;

import com.konkuk.toastfactory.config.BaseException;
import com.konkuk.toastfactory.config.BaseResponse;
import com.konkuk.toastfactory.constant.Constant;
import com.konkuk.toastfactory.member.dto.PostMemberInfoReq;
import com.konkuk.toastfactory.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "member")
public class MemberController {

    private final MemberService memberService;

    /** 회원가입 api */
    @PostMapping()
    public BaseResponse<String> postMemberInfo(@RequestBody PostMemberInfoReq postMemberInfoReq) {
        try {
            memberService.createMemberInDB(postMemberInfoReq.getName(), postMemberInfoReq.getPassword());
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
        return new BaseResponse<>(Constant.postMemberInfoOutput);
    }
}
