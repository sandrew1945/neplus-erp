package com.neplus.framework.core.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 对象操作工具类, 继承org.apache.commons.lang3.ObjectUtils类
 * @author net
 * @version 2014-6-29
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils
{

	/**
	 *  属性复制
	 * @param target
	 * @param origin
	 */
	public static void copyProperties(Object target , Object origin)
	{
		try
		{
			BeanUtils.copyProperties(target, origin);
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 注解到对象复制，只复制能匹配上的方法。
	 * @param annotation
	 * @param object
	 */
	public static void annotationToObject(Object annotation, Object object)
	{
		if (annotation != null)
		{
			Class<?> annotationClass = annotation.getClass();
			Class<?> objectClass = object.getClass();
			for (Method m : objectClass.getMethods())
			{
				if (StringUtils.startsWith(m.getName(), "set"))
				{
					try
					{
						String s = StringUtils.uncapitalize(StringUtils.substring(m.getName(), 3));
						Object obj = annotationClass.getMethod(s).invoke(annotation);
						if (obj != null && !"".equals(obj.toString()))
						{
							if (object == null)
							{
								object = objectClass.getDeclaredConstructor().newInstance();
							}
							m.invoke(object, obj);
						}
					}
					catch (Exception e)
					{
						// 忽略所有设置失败方法
					}
				}
			}
		}
	}

	/**
	 * 序列化对象
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object)
	{
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try
		{
			if (object != null)
			{
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				return baos.toByteArray();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反序列化对象
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes)
	{
		ByteArrayInputStream bais = null;
		try
		{
			if (bytes != null && bytes.length > 0)
			{
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return ois.readObject();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
