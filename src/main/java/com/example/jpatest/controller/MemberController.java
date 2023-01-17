package com.example.jpatest.controller;


import com.example.jpatest.dto.MemberDto;
import com.example.jpatest.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@RestController
//생성자 주입을 임의의 코드없이 자동으로 설정해주는 어노테이션  final필드나 @NotNull이 붙은 필드에 대해 생성자를 생성해 준다.
@RequiredArgsConstructor
@RequestMapping("jpa-test")
public class MemberController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @RequiredArgsConstuctor를 사용하지 않을 시
     * @Autowired
     * @Autowired public MemberController(MemberService memberService){
     * this.memberService = memberService;
     * }
     */
    private final MemberService memberService;


    // 모든 회원 조회
    /**
     * produces={} 해당 형태의 유형으로 응답을 한다는 것을 의미한다.
     * */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<MemberDto>> getAllmembers() {
        List<MemberDto> member = memberService.findAll();
        return new ResponseEntity<List<MemberDto>>(member, HttpStatus.OK);
    }

    //회원번호로 한명의 회원 조회
    @GetMapping(value = "/{mbrNo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MemberDto> getMember(@PathVariable("mbrNo") Long mbrNo) {
        Optional<MemberDto> member = memberService.findById(mbrNo);
        return new ResponseEntity<MemberDto>(member.get(), HttpStatus.OK);
    }

    //회원 등록
    @PostMapping
    public ResponseEntity<MemberDto> insert(@RequestBody MemberDto memberDto) {
        return new ResponseEntity<MemberDto>(memberService.save(memberDto), HttpStatus.OK);
    }

    //회원수정
    @PutMapping(value = "/{mbrNo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MemberDto> updateMember(@PathVariable("mbrNo") Long mbrNo, @RequestBody MemberDto memberDto) {
        memberService.updateById(mbrNo, memberDto);
        return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
    }

    //회원번호로 회원 삭제
    //작업 중에 하나라도 실패할 경우 전체 작업을 취소한다는 뜻
    @Transactional
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteMember(@PathVariable("id") String id) {
        memberService.deleteMember(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    //회원아이디와 이름으로 회원 조회
    @GetMapping(value = "/id", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MemberDto> findByIdAndByName(@RequestBody MemberDto memberDto) {
        Optional<MemberDto> member = memberService.findByIdAndByName(memberDto.getId(), memberDto.getName());
        return new ResponseEntity<MemberDto>(member.get(),HttpStatus.OK);
    }
}
