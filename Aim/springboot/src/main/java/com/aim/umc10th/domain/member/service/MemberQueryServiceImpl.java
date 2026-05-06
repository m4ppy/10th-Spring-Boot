package com.aim.umc10th.domain.member.service;


import com.aim.umc10th.domain.member.entity.Member;
import com.aim.umc10th.domain.member.repository.MemberMissionRepository;
import com.aim.umc10th.domain.member.repository.MemberRepository;
import com.aim.umc10th.domain.mission.entity.MemberMission;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.aim.umc10th.domain.member.enums.MissionStatus;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Page<MemberMission> getMissionList(Long memberId, MissionStatus status, Integer page){
        //1.회원 존재 여부 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new RuntimeException("해당 회원이 존재하지 않습니다."));

        return memberMissionRepository.findAllByMemberAndStatus(member, status, PageRequest.of(page, 10));
    }
}
