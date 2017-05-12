package br.com.dcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Informações de Usuário.<br>
 * A estrutura desta tabela foi compatibilizada ao padrão recomendado pelo spring security<br>
 * veja: <a href="http://docs.spring.io/spring-security/site/docs/3.2.3.RELEASE/reference/htmlsingle/#user-schema">http://docs.spring.io/spring-security/site/docs/3.2.3.RELEASE/reference/htmlsingle/#user-schema</a>
 *
 * @author Daniel Cristaldo Martinez
 * @since 10/05/2017
 */
@Entity
public class User implements Serializable {

    @Id
    @Column(name = "username", length = 20)
    private String login;

    @Column(length = 100)
    private String name;


    @Column(length = 20)
    private String password;

    private Boolean enabled;

    @Column(length = 50)
    private String authority;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
