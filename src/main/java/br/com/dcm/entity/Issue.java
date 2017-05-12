package br.com.dcm.entity;

import br.com.dcm.common.Constants;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Entidade de Persistência para tabela task.<br>
 * Guarda as informações da tarefa.
 * @author Daniel Cristaldo Martinez
 * @since 10/05/2017
 *
 */
@NamedQueries({
    @NamedQuery(name=Constants.db.querys.ticket.listUncompleted_id,query = Constants.db.querys.ticket.listUncompleted_sql)
})
@Entity
public class Issue{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private Long idTask;

    @Column(name = "create_on")
    private Calendar createdOn;

    @Lob
    private String request;

    @Lob
    private String response;

    @Column(name = "work_complete")
    private Boolean workComplete;

    @ManyToOne
    private User owner;

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public Calendar getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Boolean getWorkComplete() {
        return workComplete;
    }

    public void setWorkComplete(Boolean workComplete) {
        this.workComplete = workComplete;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
