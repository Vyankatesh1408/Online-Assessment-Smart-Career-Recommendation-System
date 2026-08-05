package com.vyankatesh.careerrecommendationsystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vyankatesh.careerrecommendationsystem.entity.Stream;
import com.vyankatesh.careerrecommendationsystem.exception.ResourceNotFoundException;
import com.vyankatesh.careerrecommendationsystem.repository.StreamRepository;
import com.vyankatesh.careerrecommendationsystem.service.StreamService;

@Service
public class StreamServiceImpl implements StreamService {

    private final StreamRepository streamRepository;

    public StreamServiceImpl(StreamRepository streamRepository) {
        this.streamRepository = streamRepository;
    }

    @Override
    public Stream createStream(Stream stream) {

        if (streamRepository.existsByStreamName(stream.getStreamName())) {
            throw new IllegalArgumentException(
                    "Stream already exists with name: " + stream.getStreamName());
        }

        return streamRepository.save(stream);
    }

    @Override
    public Stream getStreamById(Long id) {
        return streamRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Stream not found with id: " + id));
    }

    @Override
    public List<Stream> getAllStreams() {
        return streamRepository.findAll();
    }

    @Override
    public Stream updateStream(Long id, Stream stream) {

        Stream existingStream = streamRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Stream not found with id: " + id));

        existingStream.setStreamName(stream.getStreamName());
        existingStream.setDescription(stream.getDescription());

        return streamRepository.save(existingStream);
    }

    @Override
    public void deleteStream(Long id) {

        Stream stream = streamRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Stream not found with id: " + id));

        streamRepository.delete(stream);
    }
}