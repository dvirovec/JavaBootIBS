package inforbis.erp.controller.base;

import inforbis.erp.model.base.Client;
import inforbis.erp.model.base.User;
import inforbis.erp.model.base.UserDetail;
import inforbis.erp.service.base.client.ClientService;
import inforbis.erp.service.base.user.SecurityService;
import inforbis.erp.service.base.user.UserDetailsServiceImpl;
import inforbis.erp.service.base.user.UserService;
import inforbis.erp.service.base.user_detail.UserDetailService;
import inforbis.erp.service.tools.MailService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by dvirovec on 15.10.2016..
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private SecurityService securtityService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientService clientService;

    public UserController() {

    }

    @RequestMapping(value = "erp/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<User>> getUsers() {

        Iterable<User> users = userService.findAll();

        return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "erp/user/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {

        if (id == -1)
            return new ResponseEntity<User>(new User(), HttpStatus.OK);

        User user = userService.findOne(id);

        user.setUserDetails(userDetailService.findOne(id));

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "erp/logout", method = RequestMethod.GET)
    public void logout(HttpSession session) {

        session.invalidate();

    }

    @RequestMapping(value = "erp/check_session/{id}/{jSessionId}", method = RequestMethod.GET)
    public Boolean checkSession(@PathVariable("id") Long id, @PathVariable("jSessionId") String jSessionId, HttpSession session) {

        Long user_id = (Long) session.getAttribute("userId");

        if (user_id != null) {
            if (user_id == id && jSessionId.equals(session.getId())) {
                return true;
            }
        }

        return false;
    }


    @RequestMapping(value = "erp/login/{username}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> login(@PathVariable("username") String username, @PathVariable("password") String password, HttpSession session) {

        HttpStatus status = HttpStatus.OK;

        securtityService.autologin(username, password);

        Iterable<Client> clients = clientService.findClientByName("INFORBIS");

        if (!clients.iterator().hasNext()) {
            return new ResponseEntity<User>(new User(), HttpStatus.UNAUTHORIZED);
        }

        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

            Client client = clients.iterator().next();

            session.setAttribute("userId", userDetailsService.getUser().getId());
            session.setAttribute("username", userDetailsService.getUser().getName());
            session.setAttribute("client", client);

            userDetailsService.getUser().setSessionId(session.getId());

            return new ResponseEntity<User>(userDetailsService.getUser(), status);
        } else {
            status = HttpStatus.UNAUTHORIZED;
            return new ResponseEntity<User>(new User(), status);
        }

    }


    @RequestMapping(value = "erp/user_detail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetail> get_user_detail(@PathVariable("id") Long id) {

        UserDetail userDetail = userDetailService.findOne(id);

        return new ResponseEntity<UserDetail>(userDetail, HttpStatus.OK);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping(value = "/erp/register", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));

        User savedUser = userService.create(user);

        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/erp/user", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User savedUser = userService.create(user);

        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);

    }


    @RequestMapping(value = "/erp/user_detail", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetail> updateUserDetail(@RequestBody UserDetail user_detail) {

        UserDetail savedDetail = userDetailService.update(user_detail);

        return new ResponseEntity<UserDetail>(savedDetail, HttpStatus.OK);

    }


}
