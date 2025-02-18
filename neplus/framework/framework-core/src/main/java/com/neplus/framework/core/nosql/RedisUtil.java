package com.neplus.framework.core.nosql;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by summer on 2018/2/21.
 */
@Component
@Log4j2
public class RedisUtil
{
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @Description: 批量删除缓存
     * @Author: hj
     * @Date: 17:13 2017/10/24
     */
    public void remove(final String... keys)
    {
        for (String key : keys)
        {
            remove(key);
        }
    }

    /**
     * @Description: 批量删除缓存(通配符)
     * @Author: hj
     * @Date: 16:52 2017/10/24
     */
    public void removePattern(final String pattern)
    {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
        {
            redisTemplate.delete(keys);
        }
    }

    /**
     * @Description: 删除缓存
     * @Author: hj
     * @Date: 16:51 2017/10/24
     */
    public void remove(final String key)
    {
        if (exists(key))
        {
            redisTemplate.delete(key);
        }
    }

    /**
     * @Description: 判断缓存中是否有对应的value
     * @Author: hj
     * @Date: 16:50 2017/10/24
     */
    public boolean exists(final String key)
    {
        return redisTemplate.hasKey(key);
    }

    /**
     * @Author summer
     * @Description 获取缓存中所有符合条件的key
     * @Date 15:09 2023/2/23
     * @Param prefix 如果想要过滤全部则使用null、空字符串或*
     * @return java.util.Set<java.lang.String>
     **/
    public Set<String> getKeys(final String prefix)
    {
        String pattern = null;
        if(!"*".equals(prefix))
        {
            pattern = prefix;
        }
        return redisTemplate.keys(StringUtils.isEmpty(pattern) ? "*" : pattern.concat("*"));
    }

    /**
     * @Description: 读取缓存
     * @Author: hj
     * @Date: 16:49 2017/10/24
     */
    public Object get(final String key)
    {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * @Description: 写入缓存
     * @Author: hj
     * @Date: 16:48 2017/10/24
     */
    public boolean set(final String key, Object value)
    {
        boolean result = false;
        try
        {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Description: 写入缓存(可以配置过期时间)
     * @Author: hj
     * @Date: 16:46 2017/10/24
     */
    public boolean set(final String key, Object value, Long expireTime)
    {
        boolean result = false;
        try
        {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
