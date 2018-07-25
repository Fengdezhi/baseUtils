package pers.feng.baseUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ObjectUtils {
	private static Logger logger = LoggerFactory.getLogger(ObjectUtils.class);
	
	private ObjectUtils(){}
	
	/**
	 * �������л�
	 * @param object ʵ����Serializable�ӿڵĶ���
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			logger.error("���л�ʧ��", e);
		}
		return null;
		 
	}
	
	/**
	 * �������л�
	 * @param bytes
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unserialize(byte[] bytes, Class<T>  clazz) {
		
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		} catch (Exception e) {
			logger.error("�����л�ʧ��", e);
		}
		return null;
	}
	
	/**
	 * �������л�
	 * @param bytes
	 * @param clazz
	 * @return
	 */
	public static Object unserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			logger.error("�����л�ʧ��", e);
		}
		return null;
	}
	
}
