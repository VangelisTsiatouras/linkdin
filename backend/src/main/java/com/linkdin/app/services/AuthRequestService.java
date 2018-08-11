package com.linkdin.app.services;

import com.linkdin.app.dto.UserIdentifiers;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthRequestService {
    public boolean authenticateRequest(UserIdentifiers userIdentifiers, HttpSession session) {
        if (session == null) {
            return false;
        }

        if (userIdentifiers.userToken == null || userIdentifiers.id == null) {
            return false;
        }

        if (session.getAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME).equals(userIdentifiers.id) &&
                session.getAttribute("userToken").equals(userIdentifiers.userToken)) {
            System.err.println("user is authenticated!");
            return true;
        } else {
            System.err.println("user is NOT authenticated!");
            return false;
        }
    }
}
