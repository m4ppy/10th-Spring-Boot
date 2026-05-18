package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final MemberConverter memberConverter;

    @Transactional
    public MemberResponseDTO.GetMyInfo signup(MemberRequestDTO.Signup dto) {

        Member member = memberConverter.toMember(dto);

        Member savedMember = memberRepository.save(member);

        return memberConverter.toMyInfo(savedMember);
    }

    public MemberResponseDTO.Login login(MemberRequestDTO.Login dto) {

        Member member = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!member.getPassword().equals(dto.getPassword())) {
            throw new MemberException(MemberErrorCode.PASSWORD_NOT_MATCH);
        }

        return memberConverter.toLogin(member);
    }
}