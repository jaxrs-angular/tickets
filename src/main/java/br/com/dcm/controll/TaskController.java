package br.com.dcm.controll;

import br.com.dcm.bo.IssueBo;
import br.com.dcm.common.SessionData;
import br.com.dcm.entity.Issue;
import br.com.dcm.entity.User;
import br.com.dcm.exception.InternalServerException;
import br.com.dcm.to.InitTo;
import br.com.dcm.to.UserTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Controlador de microservicos rest de tarefas.<br>
 * Atende as requisicoes a /service/task/*.<br><br>
 * <b>Metodos:</b>
 * <ul>
 * <li>GET - lista as tasks não completadas</li>
 * <li>POST - cria uma nova task</li>
 * <li>HEAD - associa|dessocia|completa uma task</li>
 * </ul>
 * <br>
 * ps. usa uma pseudo sessao acoplando spring-session ao redis.<br>
 * permitindo usar a infraestrutura de sessao como banco de dados chave-valor de <br>alto desempenho e clusterizável.
 *
 * @author Daniel Cristaldo Martinez
 * @since 10/05/2017
 */
@RequestMapping(value = "/task")
@RestController
public class TaskController {

    static {
        logger = LoggerFactory.getLogger(TaskController.class);
    }

    /**
     * Log para a classe
     */
    public static final Logger logger;


    /**
     * Regras de negócio para task
     */
    @Autowired
    private IssueBo issueBo;


    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/init", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private InitTo getCurrentUser() {
        return new InitTo(this.listAllTaskUncompleted(),new UserTo(((SessionData) session.getAttribute("sessionData")).getCurrentUser()));
    }


    /**
     * Lista somente as tasks pendentes.<br>
     * Task.workComplete = null ou false é considerada pendente.<br>
     * Task.workComplete = true é considerada completada.<br>
     *
     * @return List&lt;Task&gt; lista de tasks
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private Issue save(@RequestBody Issue issue) {
        Issue res;
        try {
            res = issueBo.save(issue);
        } catch (Exception e) {
            logger.error("Erro ao listar tarefas.");
            throw new InternalServerException();
        }
        return res;
    }



    /**
     * Lista somente as tasks pendentes.<br>
     * Task.workComplete = null ou false é considerada pendente.<br>
     * Task.workComplete = true é considerada completada.<br>
     *
     * @return List&lt;Task&gt; lista de tasks
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private List<Issue> listAllTaskUncompleted() {
        List<Issue> res = null;
        try {
            res = issueBo.listUncompleted();
        } catch (Exception e) {
            logger.error("Erro ao listar tarefas.");
        }
        return res;
    }


    /**
     * Associa o usuário corrente a task indicada.<br>
     * O usuário corrente é obtido do redis
     *
     * @param taskId Id da task
     * @return ResponseEntity&lt;String&gt; "ASSIGNED"
     */
    @RequestMapping(value = "/assign", method = RequestMethod.HEAD, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<String> assignToCurrentUser(@RequestParam(value = "id") Long taskId) {
        try {
            issueBo.assignUser(taskId);
        } catch (Exception e) {
            logger.error("Erro ao associar user a task", e);

        }
        return ResponseEntity.ok("ASSIGNED");
    }


    /**
     * Desassocia a task indicada do usuario corrente.
     *
     * @param taskId Id da task
     * @return ResponseEntity&lt;String&gt; "UNASSIGNED"
     */
    @RequestMapping(value = "/unassign", method = RequestMethod.HEAD, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<String> unassignFromCurrentUser(@RequestParam(value = "id") Long taskId) {
        try {
            issueBo.unassignUser(taskId);
        } catch (Exception e) {
            logger.error("Erro ao dissociar user da task", e);

        }
        return ResponseEntity.ok("UNASSIGNED");
    }


    /**
     * Completa a task indicada.
     *
     * @param taskId Id da task
     * @return ResponseEntity&lt;String&gt; "COMPLETED"
     */
    @RequestMapping(value = "/complete", method = RequestMethod.HEAD, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    private ResponseEntity<String> completeTask(@RequestParam(value = "id") Long taskId) {
        try {
            issueBo.complete(taskId);
        } catch (Exception e) {
            logger.error("Erro ao completar task", e);

        }
        return ResponseEntity.ok("COMPLETED");
    }


}
