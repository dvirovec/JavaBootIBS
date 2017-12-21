package inforbis.erp.repository.financial.posting;

import inforbis.erp.model.financial.posting.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by dvirovec on 21.6.2017..
 */
public interface PostingRepository extends JpaRepository<Posting, Long> {

    @Query("select a from Posting a where a.client_id = :client_id")
    Iterable<Posting> findAll(@Param("client_id") Long client_id);

}
