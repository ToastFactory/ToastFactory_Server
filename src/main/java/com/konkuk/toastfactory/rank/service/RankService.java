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

/**
 * [Java = Redis ZSET]
 * VariableName = key
 * Key = value
 * Value = score
 * */

@Service
@RequiredArgsConstructor
public class RankService {

    private final RedisTemplate redisTemplate;

    @Transactional
    public void create(String name , Long score) {
        redisTemplate.opsForZSet().add("ranking", name, score);
    }

    /**
     * 1위부터 10위까지의 랭킹을 보여준다.
     * @param offset paging 처리를 위한 offset. 0부터 시작한다.
     * @return rank, name, score 정보를 랭킹이 높은 순서 가지고 있다.
     * */
    public List<RankingResDto> getRankingList(long offset) {
        String key = "ranking";
        final Long min = 0L;
        final Long max = 1000000000L;
        final Long count = 10L;

        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuplesOffset = zSetOperations.reverseRangeByScoreWithScores(key, min, max, (offset * count), count);

        return typedTuplesOffset.stream()
                .map(v -> RankingResDto.builder()
                        .rank(getMyRank(v.getValue()))  // 각 사용자의 랭킹을 계산
                        .name(v.getValue())
                        .score(v.getScore())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 원하는 사용자의 랭킹을 보여준다.
     * @return 사용자의 랭킹
     * */
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
