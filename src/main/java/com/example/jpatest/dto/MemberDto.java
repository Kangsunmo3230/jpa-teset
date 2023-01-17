package com.example.jpatest.dto;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;



/**
 * @NoArgsConstructor => 파라미터가 없는 기본 생성자를 만들어준다.
 * ex) MemberDto memberDto = new MemberDto();
 *
 * @RequiredArgsConstructor => 특별한 처리가 필요한 field 마다 하나의 parameter를 갖는 생성자를 생성해준다.
 * 초기화되지 않은 모든 final field와, 선언될 떄 초기화되지 않은 @NonNull로 표기된 field까지 parameter를 가진다.
 *
 * @AllargsConstuctor => 모든 필드 값을 파라미터로 받는 생성자를 만듦
 * MemberDto memberDto = new MemberDto(1,id,name);
 *
 *
 * */

@Data
@AllArgsConstructor //필드에 쓴 모든생성자만 만들어줌
@NoArgsConstructor(access = AccessLevel.PROTECTED) //파라미터가 없는 기본 생성자를 만들어줌
@Entity(name="member") //테이블 name
public class MemberDto {

    //pk
    @Id
    //기본키 생성을 db에 위임
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mbr_no")
    private Long mbrNo;

    private String id;

    private String name;

    @Builder
    public MemberDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
