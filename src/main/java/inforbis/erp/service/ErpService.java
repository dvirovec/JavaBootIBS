package inforbis.erp.service;

import inforbis.erp.model.base.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by dvirovec on 26.5.2017..
 */
@Service
public abstract class ErpService {

    @Autowired
    private HttpSession session;

    public Long getClientId() {
        return ((Client)session.getAttribute("client")).getId();
    }

    public Client getClient() {
        return (Client)session.getAttribute("client");
    }

}
