package caps.tf.controller;

import caps.tf.annotation.UserId;
import caps.tf.dto.wiki.request.CreateWikiRequestDto;
import caps.tf.dto.wiki.request.PatchWikiRequestDto;
import caps.tf.service.wiki.WikiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{wikiId}")
    public ResponseEntity<?> updateWiki(
            @PathVariable("wikiId") UUID wikiId,
            @Valid @RequestBody PatchWikiRequestDto patchWikiRequestDto
    ) {
        return ResponseEntity.ok(
                wikiService.patchWiki(
                        wikiId,
                        patchWikiRequestDto
                )
        );
    }

    @GetMapping
    public ResponseEntity<?> getWikiList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "dept", required = false) String department
    ){
        return ResponseEntity.ok(
                wikiService.getWikiList(
                        page,
                        name,
                        department
                )
        );
    }

    @GetMapping("/{wikiId}")
    public ResponseEntity<?> getWikiDetail(
            @PathVariable("wikiId") UUID wikiId
    ) {
        return ResponseEntity.ok(
                wikiService.getWikiDetail(wikiId)
        );
    }
}