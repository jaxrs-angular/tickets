package br.com.dcm.to;

import br.com.dcm.entity.User;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by dmartinez on 12/05/2017.
 */
public class UserTo{
        private String login;
        private String name;

    public UserTo(User user) {
        this.login = user.getLogin();
        this.name = user.getName();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
