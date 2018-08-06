package com.linkdin.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkdin.app.dto.NewPostData;
import com.linkdin.app.dto.UserAttributes;
import com.linkdin.app.model.User;
import com.linkdin.app.services.AuthRequestService;
import com.linkdin.app.services.PostService;
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
public class NewPostController {

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    AuthRequestService authRequestService;

    @PostMapping(path = "/newpost")
    public ResponseEntity<Object> newPost(@RequestBody String jsonNewPost, HttpSession session) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject obj = new JSONObject(jsonNewPost);
        try {
            JSONObject userObj = obj.getJSONObject("userAttrs");
            JSONObject postObj = obj.getJSONObject("postData");
            UserAttributes userAttributes = objectMapper.readValue(userObj.toString(), UserAttributes.class);
            NewPostData newPostData = objectMapper.readValue(postObj.toString(), NewPostData.class);

            // Authenticate user
            if (!authRequestService.authenticateRequest(userAttributes, session)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            User user = userService.returnUser(userAttributes.email);
            if (!postService.createPost(newPostData, user)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}