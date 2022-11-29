package com.konkuk.toastfactory.rank.controller;

import com.konkuk.toastfactory.config.BaseResponse;
import com.konkuk.toastfactory.constant.Constant;
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
    public BaseResponse<String> setScore(@RequestParam("name") String name, @RequestParam("score") int score) {
        rankService.create(name,score);
        return new BaseResponse<>(Constant.saveScoreOutput);
    }

    /** 점수 List 가져오기 api */
    @GetMapping("")
    public BaseResponse<List<RankingResDto>> getRanking(@RequestParam("offset") Long offset) {
        return new BaseResponse<>(rankService.getRankingList(offset));
    }

    /** 멤버별 점수 가져오기 api */
    @GetMapping("/my")
    public BaseResponse<Long> getMyRanking(@RequestParam("name") String name) {
        return new BaseResponse<>(rankService.getMyRank(name));
    }

}
