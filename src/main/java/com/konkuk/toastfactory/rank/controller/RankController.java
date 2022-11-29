package com.konkuk.toastfactory.rank.controller;

import com.konkuk.toastfactory.rank.dto.RankingResDto;
import com.konkuk.toastfactory.rank.service.RankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "rank")
public class RankController {

    private final RankService rankService;

    /** 점수 설정 api */
    @PatchMapping("/score")
    public void setScore(@RequestParam("name") String name, @RequestParam("score") int score) {
        rankService.create(name,score);
    }

    /** 점수 List 가져오기 api */
    @GetMapping("")
    public List<RankingResDto> getRanking() {
        return rankService.getRankingList();
    }

    /** 멤버별 점수 가져오기 api */
    @GetMapping("/my")
    public Long getMyRanking(@RequestParam("name") String name) {
        System.out.println("name = " + name);
        return rankService.getMyRank(name);
    }

}
