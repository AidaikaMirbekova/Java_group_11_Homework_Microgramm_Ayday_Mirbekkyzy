package com.example.micrigramm.Service;

import com.example.micrigramm.DTO.PublicationDTO;
import com.example.micrigramm.Entity.Publication;
import com.example.micrigramm.Exception.ResourceNotFoundException;
import com.example.micrigramm.Repository.CommentRepository;
import com.example.micrigramm.Repository.LikeRepository;
import com.example.micrigramm.Repository.PublicationRepository;
import com.example.micrigramm.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
    private final LikeRepository likeRepository;

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
        Integer counter = 1;
        Integer publicationCounter = user.getCountPublications() + counter;
        user.setCountPublications(publicationCounter);
        publicationRepository.save(publication);
        userRepository.save(user);
    }

    public PublicationDTO findPublication(Long publicationId) {
        var publication = publicationRepository.findPublicationById(publicationId);
        if (publication == null) {
            throw new ResourceNotFoundException("Can't find movie with the ID: " + publicationId);
        }
        return PublicationDTO.from(publication);
    }

    public boolean deletePublication(Long publicId, String useremail) throws Exception {
        var user = userRepository.findByEmailContainsIgnoringCase(useremail);
        var publication = publicationRepository.findPublicationById(publicId);
        if (!publication.getAuthor().getId().equals(user.getId())) {
            throw new Exception("You can't delete another user's post");
        }
        commentRepository.deleteCommentsByPublicationId(publicId);
        publicationRepository.deletePublicationByIdAndAuthorId(publicId, user.getId());
        likeRepository.deleteLikeByPublicationId(publication.getId());
        return true;
    }

    public Slice<PublicationDTO> showAllMovies(Long userId, Pageable pageable) {
        var slica = publicationRepository.findAllByAuthorId(userId, pageable);
        return slica.map(PublicationDTO::from);
    }
}
