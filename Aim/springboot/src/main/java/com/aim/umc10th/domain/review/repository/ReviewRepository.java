package com.aim.umc10th.domain.review.repository;

import com.aim.umc10th.domain.review.entity.Review;
import com.aim.umc10th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    //가게별 리뷰 조회(페이징 포함)
    @Query("SELECT r FROM Review r WHERE r.store = :store")
    Page<Review> findAllByStore(@Param("store") Store store, Pageable pageable);
}
