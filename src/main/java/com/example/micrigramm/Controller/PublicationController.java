package com.example.micrigramm.Controller;

import com.example.micrigramm.DTO.PublicationDTO;
import com.example.micrigramm.Service.PublicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/publications")
public class PublicationController {
    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping("/newPublic")
    public ResponseEntity<String> addPublic(@RequestParam("file") MultipartFile file, String email , String description) throws Exception {
       publicationService.addPublic(file,email,description);
        return ResponseEntity.ok().build();

    }
    @GetMapping("/{publicationId}")
    public PublicationDTO getPublication(@PathVariable Long publicationId) {
        return publicationService.findPublication(publicationId);
    }

    @DeleteMapping("/delete/{publicId}")
    public ResponseEntity<String> deletePublic(@PathVariable Long publicId,Long usarId) throws Exception {
        if (publicationService.deletePublication(publicId,usarId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
