package inforbis.erp.service.base.user;

/**
 * Created by dvirovec on 2.4.2017..
 */
public interface SecurityService {

    String findLoggedInUsername();
    void autologin(String username, String password);
}
