package com.example.micrigramm.Repository;


import com.example.micrigramm.Entity.Publication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface PublicationRepository extends PagingAndSortingRepository<Publication, Long> {

    Publication findPublicationById(Long publicationId);
    int deletePublicationByIdAndAuthorId(Long pubId, Long userId);
    Slice<Publication> findAllByAuthorId(Long userId, Pageable pageable);
}
