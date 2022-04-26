package com.example.micrigramm.Repository;


import com.example.micrigramm.Entity.Publication;
import com.example.micrigramm.Entity.User;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PublicationRepository extends PagingAndSortingRepository<Publication, Long> {

    Publication findPublicationById(Long publicationId);

    void deletePublicationByIdAndAuthorId(Long pubId, Long userId);

    Slice<Publication> findAllByAuthorEmail(String user, Pageable pageable);
}
