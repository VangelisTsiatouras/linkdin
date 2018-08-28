package com.linkdin.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkdin.app.dto.ListUsers;
import com.linkdin.app.dto.UserIdentifiers;
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

@RestController
public class SearchController {

    @Autowired
    UserService userService;
    @Autowired
    AuthRequestService authRequestService;

    @PostMapping(path = "/searchusers")
    public ResponseEntity<Object> search(@RequestBody String jsonSearchRequest, HttpSession session) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject obj = new JSONObject(jsonSearchRequest);
        try {
            JSONObject userObj = obj.getJSONObject("userIdentifiers");
            JSONObject searchObj = obj.getJSONObject("searchData");
            UserIdentifiers userIdentifiers = objectMapper.readValue(userObj.toString(), UserIdentifiers.class);
            String searchQuery = searchObj.getString("searchQuery");

            // Authenticate user
            if (!authRequestService.authenticateRequest(userIdentifiers, session)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            ListUsers result = userService.searchUsers(searchQuery);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}