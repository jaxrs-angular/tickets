package br.com.dcm.bo;

import br.com.dcm.common.SessionData;
import br.com.dcm.dao.IssueDao;
import br.com.dcm.entity.Issue;
import br.com.dcm.entity.User;
import br.com.dcm.exception.InternalServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Centralização das regras de negócio das tarefas.<br>
 *
 * @author Daniel Cristaldo Martinez
 * @since 10/05/2017
 */
@Component
@Transactional
public class IssueBo {
    private static final Logger logger;

    static {
        logger = LoggerFactory.getLogger(IssueBo.class);
    }

    @Autowired
    HttpSession session;

    /**
     * CrudRepository para Task (injetado)
     */
    @Autowired
    IssueDao issueDao;

    @Autowired
    private UserBo userBo;


    /**
     * lista as tarefas não completadas
     *
     * @return List&lt;Task&gt;
     */
    public List<Issue> listUncompleted() {
        try {
            return issueDao.listUncompleted();
        } catch (Exception e) {
            logger.error("Erro ao listar tarefas nao completadas!", e);
            throw new InternalServerException();
        }
    }

    public void assignUser(Long issueId) {
        try {
            User user = ((SessionData) session.getAttribute("sessionData")).getCurrentUser();
            Issue issue = issueDao.findOne(issueId);
            issue.setOwner(user);
            issueDao.save(issue);
        } catch (Exception e) {
            logger.error("Erro ao atribuir a tarefa ao usuario corrente", e);
            throw new InternalServerException();
        }
    }

    public void unassignUser(Long issueId) {
        try {
            Issue issue = issueDao.findOne(issueId);
            issue.setOwner(null);
            issueDao.save(issue);
        } catch (Exception e) {
            logger.error("Erro ao desvincular usuario da tarefa", e);
            throw new InternalServerException();
        }
    }

    public void complete(Long issueId) {
        try {
            Issue issue = issueDao.findOne(issueId);
            issue.setWorkComplete(true);
            issueDao.save(issue);
        } catch (Exception e) {
            logger.error("Erro ao completar tarefa", e);
            throw new InternalServerException();
        }
    }

    public Issue save(Issue issue) {
        if(issue.getCreatedOn()==null)
            issue.setCreatedOn(new GregorianCalendar());
        return issueDao.save(issue);
    }
}
