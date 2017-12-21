package inforbis.erp.service.financial.posting;

import inforbis.erp.model.financial.posting.Posting;

/**
 * Created by dvirovec on 21.6.2017..
 */
public interface PostingService {

    Iterable<Posting> findAll();
    Posting findOne(Long id);
    Posting create(Posting posting);
    Posting update(Posting posting);
    void delete(Long id);
    
}
