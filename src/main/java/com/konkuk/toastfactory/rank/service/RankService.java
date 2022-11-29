package com.konkuk.toastfactory.rank.service;

import com.konkuk.toastfactory.member.entity.Member;
import com.konkuk.toastfactory.rank.dto.RankingResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankService {

    private final RedisTemplate redisTemplate;

    @Transactional
    public void create(String name , int score) {
        redisTemplate.opsForZSet().add("ranking", name, score);
    }

    public List<RankingResDto> getRankingList() {
        String key = "ranking";
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples = zSetOperations.reverseRangeWithScores(key, 0, 10);
        return typedTuples.stream()
                .map(v -> RankingResDto.builder()
                        .name(v.getValue())
                        .score(v.getScore())
                        .build())
                .collect(Collectors.toList());
    }

    public Long getMyRank(String name){
        Long ranking = 0L;
        Double memberScore = redisTemplate.opsForZSet().score("ranking", name);
        Set<String> highestMemberInEqualScore = redisTemplate.opsForZSet().reverseRangeByScore("ranking", memberScore, memberScore, 0, 1);
        for (String s : highestMemberInEqualScore) {
            ranking = redisTemplate.opsForZSet().reverseRank("ranking", s);
        }
        return ranking+1;//index가 0부터 시작되어서 1 더해준다
    }
}
