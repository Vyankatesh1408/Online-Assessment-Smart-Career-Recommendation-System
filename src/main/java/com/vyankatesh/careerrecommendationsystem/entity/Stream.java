package com.vyankatesh.careerrecommendationsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "streams")
public class Stream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stream_name", nullable = false, unique = true)
    private String streamName;

    @Column(length = 500)
    private String description;

    public Stream() {
    }

    public Long getId() {
        return id;
    }

    public String getStreamName() {
        return streamName;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}