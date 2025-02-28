package caps.tf.controller;

import caps.tf.annotation.UserId;
import caps.tf.dto.wiki.request.CreateWikiRequestDto;
import caps.tf.service.wiki.WikiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/wiki")
@RequiredArgsConstructor
public class WikiController {
    private final WikiService wikiService;

    @PostMapping
    public ResponseEntity<?> createWiki(
            @UserId UUID userId,
            @RequestBody CreateWikiRequestDto createWikiRequestDto
    ) {
        return ResponseEntity.created(
                wikiService.createWiki(userId, createWikiRequestDto)
        ).build();
    }
}
