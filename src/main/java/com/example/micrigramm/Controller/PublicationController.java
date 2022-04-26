package com.example.micrigramm.Controller;

import com.example.micrigramm.DTO.PublicationDTO;
import com.example.micrigramm.Entity.User;
import com.example.micrigramm.Service.CommentService;
import com.example.micrigramm.Service.PublicationService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:63342", maxAge = 36000)
@RestController
@RequestMapping("/publications")
public class PublicationController {
    private final PublicationService publicationService;
    private final CommentService commentService;

    public PublicationController(PublicationService publicationService, CommentService commentService) {
        this.publicationService = publicationService;
        this.commentService = commentService;
    }

    @PostMapping("/addPost")
    public ResponseEntity<Void> addPublication(Authentication authentication, PublicationDTO dto,
                                               @RequestParam("file") MultipartFile file) throws Exception {
        User user = (User) authentication.getPrincipal();
        publicationService.addPublic(file, user.getUsername(), dto);
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
    public Slice<PublicationDTO> allPostsUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return publicationService.showAllPublic(user.getUsername());
    }

}
