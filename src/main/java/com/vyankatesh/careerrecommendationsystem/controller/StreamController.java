package com.vyankatesh.careerrecommendationsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.vyankatesh.careerrecommendationsystem.entity.Stream;
import com.vyankatesh.careerrecommendationsystem.service.StreamService;

@RestController
@RequestMapping("/api/streams")
public class StreamController {

    private final StreamService streamService;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Stream createStream(@RequestBody Stream stream) {
        return streamService.createStream(stream);
    }

    @GetMapping("/{id}")
    public Stream getStreamById(@PathVariable Long id) {
        return streamService.getStreamById(id);
    }

    @GetMapping
    public List<Stream> getAllStreams() {
        return streamService.getAllStreams();
    }

    @PutMapping("/{id}")
    public Stream updateStream(@PathVariable Long id,
                               @RequestBody Stream stream) {
        return streamService.updateStream(id, stream);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStream(@PathVariable Long id) {
        streamService.deleteStream(id);
    }
}