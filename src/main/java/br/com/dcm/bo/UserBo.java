package br.com.dcm.bo;

import br.com.dcm.common.SessionData;
import br.com.dcm.dao.UserDao;
import br.com.dcm.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by dmartinez on 10/05/2017.
 */
@Component
public class UserBo {
    private static final Logger logger;

    private StringRedisTemplate redisTemplate;

    static {
        logger = LoggerFactory.getLogger(UserBo.class);
    }

    @Autowired
    private UserDao userDAO;

    public User get(String name){
        return userDAO.findOne(name);
    }
}
