package com.team2.pptor.repository;

import com.team2.pptor.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // 회원가입
    public int save(Member member){
        em.persist(member);
        return member.getId();
    }

    // 회원탈퇴(Member 객체 받기)
    public int delete(Member member){
        em.remove(member);
        return member.getId();
    }

    // 회원번호로 회원탈퇴
    public int deleteById(int id){
        Member member = findById(id);
        em.remove(member);
        return member.getId();
    }

    // 회원수정
    //public int modify(Member member){
    //
    //}

    // 회원번호로 회원찾기
    public Member findById(int id){
        return em.find(Member.class, id);
    }

    // 회원 전체 리스트
    public List<Member> findAll(){
        return em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }

    // 아이디로 회원 조회
    public Optional<Member> findByLoginId(String loginId) {

        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList()
                .stream()
                .findFirst();

    }

}