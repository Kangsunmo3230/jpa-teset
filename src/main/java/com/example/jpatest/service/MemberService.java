package com.example.jpatest.service;

import com.example.jpatest.dao.MemberDao;
import com.example.jpatest.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;


    public List<MemberDto> findAll(){
        List<MemberDto> members = new ArrayList<>();
        memberDao.findAll().forEach(e -> members.add(e));
        return members;
    }

    public Optional<MemberDto> findById(Long mbrNo){
        Optional<MemberDto> member = memberDao.findById(mbrNo);
        return member;
    }

    public MemberDto save(MemberDto memberDto){
        memberDao.save(memberDto);
        return memberDto;
    }

    public void updateById(Long mbrNo, MemberDto memberDto){
        Optional<MemberDto> e = memberDao.findById(mbrNo);

        if(e.isPresent()){ //null값이 아니라면
            e.get().setMbrNo(memberDto.getMbrNo());
            e.get().setId(memberDto.getId());
            e.get().setName(memberDto.getName());
            memberDao.save(memberDto);
        }
    }

    public void deleteMember(String id){
        memberDao.deleteById(id);
    }

    public Optional<MemberDto> findByIdAndByName(String id, String name){
        Optional<MemberDto> memberDto = memberDao.findByIdAndName(id,name);
        return memberDto;
    }
}
