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
	 * 对象序列化
	 * @param object 实现了Serializable接口的对象
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
			logger.error("序列化失败", e);
		}
		return null;
		 
	}
	
	/**
	 * 对象反序列化
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
			logger.error("反序列化失败", e);
		}
		return null;
	}
	
	/**
	 * 对象反序列化
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
			logger.error("反序列化失败", e);
		}
		return null;
	}
	
}
