package com.motivank.webflux.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequestMapping("/api/v1/stream")
public class StreamingController {

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Resource>> streamMusic(@PathVariable Long id) {
        return Mono.fromSupplier(() -> {
           try {
               var root = Paths.get(System.getProperty("user.dir"));
               var musicPath = root.resolve("music").resolve("music.wav");

               var resource = new UrlResource(musicPath.toUri());

                return ResponseEntity.ok()
                          .header("Content-Type", "audio/wav")
                          .header("Content-Disposition", "inline; filename=music.wav")
                          .body(resource);
           } catch (IOException e) {
               log.error("Error while streaming music", e);

                return ResponseEntity.notFound().build();
           }
        });
    }

}
