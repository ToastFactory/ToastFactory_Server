package com.konkuk.toastfactory.member.controller;

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

    @PostMapping()
    public void postMemberInfo(@RequestBody String name, @RequestBody String password) {
        memberService.createMemberInDB(name,password);
    }
}
