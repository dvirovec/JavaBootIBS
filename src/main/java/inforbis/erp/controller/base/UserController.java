package inforbis.erp.controller.base;

import inforbis.erp.model.base.User;
import inforbis.erp.model.base.UserDetail;
import inforbis.erp.service.base.user.SecurityService;
import inforbis.erp.service.base.user.UserDetailsServiceImpl;
import inforbis.erp.service.base.user.UserService;
import inforbis.erp.service.base.user_detail.UserDetailService;
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

/**
 * Created by dvirovec on 15.10.2016..
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private SecurityService securtityService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserController() {

    }

    @RequestMapping(value="erp/users",
            method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<User>> getUsers() {

        Iterable<User> users = userService.findAll();

        return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value="erp/user/{id}",
            method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {

        User user = userService.findOne(id);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }




    @RequestMapping(value="erp/login/{username}/{password}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<User> login(@PathVariable("username") String username, @PathVariable("password") String password, HttpSession session) {

            HttpStatus status = HttpStatus.OK;

            securtityService.autologin(username,password);

             if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

                    session.setAttribute("userId",userDetailsService.getUser().getId());
                    session.setAttribute("username",userDetailsService.getUser().getName());

                return new ResponseEntity<User>(userDetailsService.getUser(), status);
            }
            else {
                status = HttpStatus.UNAUTHORIZED;
                return new ResponseEntity<User>(new User(), status);
            }

    }


    @RequestMapping(value="erp/user_detail/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<UserDetail> get_user_detail(@PathVariable("id") Long id) {

            UserDetail userDetail = userDetailService.findOne(id);

            return new ResponseEntity<UserDetail>(userDetail,HttpStatus.OK);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping(value= "/erp/register", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {

        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));

        User savedUser =  userService.create(user);

        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);

    }


}
