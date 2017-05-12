package br.com.dcm.to;

import br.com.dcm.entity.Issue;

import java.util.List;

/**
 * Created by dmartinez on 12/05/2017.
 */
public class InitTo {
    private List<Issue> list;
    private UserTo current;

    public InitTo(List<Issue> list, UserTo current) {
        this.list = list;
        this.current = current;
    }

    public List<Issue> getList() {
        return list;
    }

    public void setList(List<Issue> list) {
        this.list = list;
    }

    public UserTo getCurrent() {
        return current;
    }

    public void setCurrent(UserTo current) {
        this.current = current;
    }
}
