package com.vyankatesh.careerrecommendationsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vyankatesh.careerrecommendationsystem.entity.Stream;

public interface StreamRepository extends JpaRepository<Stream, Long> {

    Optional<Stream> findByStreamName(String streamName);

    boolean existsByStreamName(String streamName);

}