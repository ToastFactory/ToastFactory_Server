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

    /** 점수 설정 api */
    @GetMapping("")
    public List<RankingResDto> getRanking() {
        return rankService.getRankingList();
    }

}
