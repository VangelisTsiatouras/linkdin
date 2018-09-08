package com.linkdin.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkdin.app.dto.ListUsers;
import com.linkdin.app.dto.UserIdentifiers;
import com.linkdin.app.model.User;
import com.linkdin.app.services.AuthRequestService;
import com.linkdin.app.services.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AdminListAllUsersController {
    @Autowired
    UserService userService;
    @Autowired
    AuthRequestService authRequestService;

    @PostMapping(path = "/adminlistusers")
    public ResponseEntity<Object> listAllUsers(@RequestBody String jsonListUsers, HttpSession session) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject obj = new JSONObject(jsonListUsers);
        try {
            // TODO create admin auth service
            JSONObject userObj = obj.getJSONObject("adminIdentifiers");

            List list = userService.listAllUsers();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
