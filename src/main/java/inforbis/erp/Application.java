package inforbis.erp;

import inforbis.erp.service.tools.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot main Application
 *
 */
@SpringBootApplication
public class Application
{



    public static void main( String[] args ) throws Exception {
        SpringApplication.run(Application.class, args);

        // just for testing purposes
       //   MailService mailService = new MailService();
      //  mailService.sendMail();
    }


}


