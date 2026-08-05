package com.vyankatesh.careerrecommendationsystem.service;

import java.util.List;

import com.vyankatesh.careerrecommendationsystem.entity.Stream;

public interface StreamService {

    Stream createStream(Stream stream);

    Stream getStreamById(Long id);

    List<Stream> getAllStreams();

    Stream updateStream(Long id, Stream stream);

    void deleteStream(Long id);

}