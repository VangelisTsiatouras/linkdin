package com.linkdin.app.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkdin.app.dto.UserIdentifiers;
import com.linkdin.app.model.Chat;
import com.linkdin.app.services.AuthRequestService;
import com.linkdin.app.services.ChatService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class GetChatByUsersIdsController {
    @Autowired
    ChatService chatService;
    @Autowired
    AuthRequestService authRequestService;

    @PostMapping(path = "/getchatbyusersids")
    public ResponseEntity<Object> getChatByUsersIds(@RequestBody String jsonChatRequest, HttpSession session) {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject obj = new JSONObject(jsonChatRequest);
        try {
            JSONObject userObj = obj.getJSONObject("userIdentifiers");
            JSONObject chatObj = obj.getJSONObject("chat");
            UserIdentifiers userIdentifiers = objectMapper.readValue(userObj.toString(), UserIdentifiers.class);
            String userID1 = chatObj.getString("user1");
            String userID2 = chatObj.getString("user2");

            // Authenticate user
            if (!authRequestService.authenticateRequest(userIdentifiers, session)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            Chat chat = chatService.getChatIDByUsers(Integer.parseInt(userID1), Integer.parseInt(userID2));

            return new ResponseEntity<>(chat, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
