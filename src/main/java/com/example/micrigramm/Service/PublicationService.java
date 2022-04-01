package com.example.micrigramm.Service;

import com.example.micrigramm.DTO.PublicationDTO;
import com.example.micrigramm.Entity.Publication;
import com.example.micrigramm.Exception.ResourceNotFoundException;
import com.example.micrigramm.Repository.CommentRepository;
import com.example.micrigramm.Repository.PublicationRepository;
import com.example.micrigramm.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
@Transactional
@RequiredArgsConstructor
@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public void addPublic(MultipartFile file, String email, String description) throws Exception {
        byte[] data = new byte[0];
        try {
            data = file.getBytes();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        var user = userRepository.findByEmailContainsIgnoringCase(email);
        var publication = Publication.builder()
                .author(user)
                .image(data)
                .description(description)
                .dateAdded(LocalDateTime.now())
                .build();

        if (user == null) {
            throw new Exception("User not found!");
        }
        Integer counter=user.getCountPublications();
        user.setCountPublications(counter+1);
        publicationRepository.save(publication);
        userRepository.save(user);
    }

    public PublicationDTO findPublication(Long publicationId) {
        var publication = publicationRepository.findPublicationById(publicationId);
        if (publication == null) {
       throw new ResourceNotFoundException("Can't find movie with the ID: " + publicationId);}
        return PublicationDTO.from(publication);
    }

    public boolean deletePublication(Long publicId, Long autorId) throws Exception {
        var publication = publicationRepository.findPublicationById(publicId);
        if (!publication.getAuthor().getId().equals(autorId)){
        throw  new Exception("You can't delete another user's post");}
        commentRepository.deleteCommentsByPublicationId(publicId);
        publicationRepository.deletePublicationByIdAndAuthorId(publicId, autorId);
        return true;
    }
}
