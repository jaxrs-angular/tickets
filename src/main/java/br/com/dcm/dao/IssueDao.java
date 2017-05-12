package br.com.dcm.dao;

import br.com.dcm.common.Constants;
import br.com.dcm.entity.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO para tabela Task.<br>
 * Interface injetável de manipulação dos dados da tabela Task.
 *
 * @author Daniel Cristaldo Martinez
 * @since 10/05/2017
 */
public interface IssueDao extends CrudRepository<Issue, Long> {

    /**
     * Lista todas as tarefas pendentes
     *
     * @return List&lt;Task&gt;
     */
    @Query(name = Constants.db.querys.ticket.listUncompleted_id)
    List<Issue> listUncompleted();

}
