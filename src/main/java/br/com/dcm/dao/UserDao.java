package br.com.dcm.dao;

import br.com.dcm.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Dao para tabela User.<br>
 * Inteface injetável de manipulação da tabela User
 */
public interface UserDao extends CrudRepository<User, String> {

}
