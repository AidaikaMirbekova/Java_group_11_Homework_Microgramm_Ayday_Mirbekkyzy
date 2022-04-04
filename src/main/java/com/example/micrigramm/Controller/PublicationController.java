package com.example.micrigramm.Controller;

import com.example.micrigramm.DTO.PublicationDTO;
import com.example.micrigramm.Entity.User;
import com.example.micrigramm.Service.PublicationService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/publications")
public class PublicationController {
    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping("/api/addNewPublic")
    public ResponseEntity<String> addPublic(@RequestParam("file") MultipartFile file, String description, Authentication authentication) throws Exception {
        User user = (User) authentication.getPrincipal();
        publicationService.addPublic(file, user.getUsername(), description);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/showOnePublication/{publicationId}")
    public PublicationDTO getPublication(@PathVariable Long publicationId) {
        return publicationService.findPublication(publicationId);
    }

    @DeleteMapping("/api/deletePublication/{publicId}")
    public ResponseEntity<String> deletePublic(@PathVariable Long publicId, Authentication authentication) throws Exception {
        User user = (User) authentication.getPrincipal();
        if (publicationService.deletePublication(publicId, user.getUsername())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/showAllUserPosts")
    public Slice<PublicationDTO> allPostsUser(@RequestParam Long userId, Pageable pageable) {
        return publicationService.showAllMovies(userId, pageable);
    }

}
