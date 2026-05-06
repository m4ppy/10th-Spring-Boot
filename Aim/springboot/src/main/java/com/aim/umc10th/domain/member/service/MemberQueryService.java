package com.aim.umc10th.domain.member.service;

import com.aim.umc10th.domain.member.enums.MissionStatus;
import com.aim.umc10th.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;

public interface MemberQueryService {
    Page<MemberMission> getMissionList(Long memberId, MissionStatus status, Integer page);
}
