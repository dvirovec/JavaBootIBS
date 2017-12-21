package inforbis.erp.service.financial.posting;

import inforbis.erp.model.financial.posting.Posting;
import inforbis.erp.repository.financial.posting.PostingRepository;
import inforbis.erp.service.ErpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dvirovec on 21.6.2017..
 */
@Service
public class PostingServiceBean extends ErpService implements PostingService {

    @Autowired
    private PostingRepository postingRepository;

    @Override
    public Iterable<Posting> findAll() {

        Iterable<Posting> postings = postingRepository.findAll(getClientId());

        return postings;
    }

    @Override
    public Posting findOne(Long id) {

        Posting posting = postingRepository.findOne(id);

        return posting;
    }

    @Override
    public Posting create(Posting posting) {

        if(posting.getId()!=null) {
            return null;
        }

        posting.setClient_id(getClientId());

        Posting savedPosting = postingRepository.save(posting);

        return savedPosting;

    }

    @Override
    public Posting update(Posting posting) {

        Posting postingPersisted = findOne(posting.getId());

        if(postingPersisted == null){
            return null;
        }

        Posting updatedPosting = postingRepository.save(posting);

        return updatedPosting;
    }

    @Override
    public void delete(Long id) {

        postingRepository.delete(id);

    }
    
}
