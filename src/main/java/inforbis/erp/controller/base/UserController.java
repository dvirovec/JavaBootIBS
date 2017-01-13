package inforbis.erp.controller.base;

import inforbis.erp.model.base.User;
import inforbis.erp.model.base.UserDetail;
import inforbis.erp.service.base.user.UserService;
import inforbis.erp.service.base.user_detail.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dvirovec on 15.10.2016..
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailService;


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
        public ResponseEntity<User> login(@PathVariable("username") String username, @PathVariable("password") String password) {

        User user = userService.findByName(username);

        HttpStatus status;

        if(user.getPassword().equals(password)) {
            status = HttpStatus.OK;
        }
        else {
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<User>(user, status);
    }

    @RequestMapping(value="erp/user_detail/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<UserDetail> get_user_detail(@PathVariable("id") Long id) {

            UserDetail userDetail = userDetailService.findOne(id);

            return new ResponseEntity<UserDetail>(userDetail,HttpStatus.OK);
    }



}
