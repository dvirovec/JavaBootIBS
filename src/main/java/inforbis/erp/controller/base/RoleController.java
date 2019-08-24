package inforbis.erp.controller.base;

import inforbis.erp.model.base.*;
import inforbis.erp.service.base.client.ClientService;
import inforbis.erp.service.base.role.RoleService;
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
public class RoleController {

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private ClientService clientService;

    public RoleController() {

    }

    @RequestMapping(value="erp/role", method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Role>> getRoles() {

        Iterable<Role> users = roleService.findAll();

        return new ResponseEntity<Iterable<Role>>(users, HttpStatus.OK);
    }

    @RequestMapping(value="erp/role/{id}",
            method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getRole(@PathVariable("id") Long id) {

        if(id==-1)
            return new ResponseEntity<Role>(new Role(), HttpStatus.OK);


        Role role = roleService.findOne(id);

        return new ResponseEntity<Role>(role, HttpStatus.OK);
    }

    @RequestMapping(value= "/erp/role", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> createRole(@RequestBody Role role) {


        Role savedRole =  roleService.create(role);

        return new ResponseEntity<Role>(savedRole, HttpStatus.CREATED);

    }

    @RequestMapping(value= "/erp/role/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> updateRole(@RequestBody Role role) {

        Role updatedRole = roleService.update(role);

        if(updatedRole==null){
            return new ResponseEntity<Role>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Role>(updatedRole, HttpStatus.OK);
    }

    @RequestMapping(value= "/erp/role/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> deleteRole(@PathVariable("id") Long id, @RequestBody Role role){

        roleService.delete(id);

        return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);

    }


}
