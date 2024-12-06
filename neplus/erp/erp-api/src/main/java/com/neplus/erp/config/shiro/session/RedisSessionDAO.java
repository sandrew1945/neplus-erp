/**
 * <pre>
 * FILE : MySqlSessionDAO.java
 * CLASS : MySqlSessionDAO
 *
 * AUTHOR : Administrator
 *
 * FUNCTION : TODO
 *
 *
 * ======================================================================
 * CHANGE HISTORY LOG
 * ----------------------------------------------------------------------
 * MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
 * ----------------------------------------------------------------------
 * 		  |2017年5月8日| Administrator| Created |
 * DESCRIPTION:
 * </pre>
 * <p/>
 * $Id: MySqlSessionDAO.java,v 0.1 2017年5月8日 上午10:35:36 Administrator Exp $
 */
/**
 * $Id: MySqlSessionDAO.java,v 0.1 2017年5月8日 上午10:35:36 Administrator Exp $
 */

package com.neplus.erp.config.shiro.session;

import com.neplus.framework.core.nosql.RedisUtil;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;


/**
 * Function    : 
 * @author     : Administrator
 * CreateDate  : 2017年5月8日
 * @version    :
 */
@Log4j2
public class RedisSessionDAO extends CachingSessionDAO
{

    private static final Long EXPIRE_TIME = 30 * 60l;


    @Resource
    private RedisUtil redisUtil;

    /* (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.CachingSessionDAO#doUpdate(org.apache.shiro.session.Session)
     */
    @Override
    protected void doUpdate(Session session)
    {
        //如果会话过期/停止 没必要再更新了
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid())
        {
            return;
        }
        redisUtil.set(String.valueOf(session.getId()), session, EXPIRE_TIME);
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.CachingSessionDAO#doDelete(org.apache.shiro.session.Session)
     */
    @Override
    protected void doDelete(Session session)
    {
        redisUtil.remove(String.valueOf(session.getId()));
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doCreate(org.apache.shiro.session.Session)
     */
    @Override
    protected Serializable doCreate(Session session)
    {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        redisUtil.set(String.valueOf(sessionId), session);
        return session.getId();
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doReadSession(java.io.Serializable)
     */
    @Override
    protected Session doReadSession(Serializable sessionId)
    {
        Session session = (Session) redisUtil.get(String.valueOf(sessionId));
        return session;
    }

}
