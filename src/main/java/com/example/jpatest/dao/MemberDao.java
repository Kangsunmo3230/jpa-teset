package com.example.jpatest.dao;

import com.example.jpatest.dto.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberDao extends JpaRepository<MemberDto, Long> {
    public void deleteById(String id);

    public Optional<MemberDto> findByIdAndName(String id, String name);
}

