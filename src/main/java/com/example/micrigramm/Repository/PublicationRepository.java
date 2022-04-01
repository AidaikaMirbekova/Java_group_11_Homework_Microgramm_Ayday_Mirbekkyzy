package com.example.micrigramm.Repository;


import com.example.micrigramm.Entity.Publication;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PublicationRepository extends PagingAndSortingRepository<Publication, Long> {

    Publication findPublicationById(Long publicationId);
    int deletePublicationByIdAndAuthorId(Long pubId, Long userId);
}
