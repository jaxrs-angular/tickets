package br.com.dcm.common;

import br.com.dcm.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.Serializable;

/**
 * Created by dmartinez on 10/05/2017.
 */
public class SessionData implements Serializable {

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
