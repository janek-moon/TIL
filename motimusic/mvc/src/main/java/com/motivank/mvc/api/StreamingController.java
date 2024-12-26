package com.motivank.mvc.api;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/stream")
public class StreamingController {

    @GetMapping("/{id}")
    public ResponseEntity<Resource> streamMusic(@PathVariable Long id) {
        try {
            var root = Paths.get(System.getProperty("user.dir"));
            var musicPath = root.resolve("music").resolve("music.wav");

            var resource = new UrlResource(musicPath.toUri());

            return ResponseEntity.ok()
                    .header("Content-Type", "audio/wav")
                    .header("Content-Disposition", "inline; filename=music.wav")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
