package pers.feng.baseUtils.json;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;


//Gson����Ŀ��ַ��https://github.com/google/gson����Gson��Ŀǰ������ȫ��Json����������Gson������Ϊ��ӦGoogle��˾�ڲ��������Google�����з����������Դ���2008�����¹���������һ����ѱ���๫˾���û�Ӧ�á�Gson��Ӧ����ҪΪtoJson��fromJson����ת��������������������Ҫ��������jar���ܹ�ֱ������JDK�ϡ�����ʹ�����ֶ���ת��֮ǰ���ȴ����ö���������Լ����Ա���ܳɹ��Ľ�JSON�ַ����ɹ�ת�������Ӧ�Ķ���������ֻҪ��get��set������Gson��ȫ���Խ��������͵�json��bean��bean��json��ת������JSON������������
//FastJson����Ŀ��ַ��https://github.com/alibaba/fastjson����Fastjson��һ��Java���Ա�д�ĸ����ܵ�JSON������,�ɰ���Ͱ͹�˾������������������Ҫ��������jar���ܹ�ֱ������JDK�ϡ�FastJson�ڸ������͵�Beanת��Json�ϻ����һЩ���⣬���ܻ�������õ����ͣ�����Jsonת��������Ҫ�ƶ����á�FastJson���ö������㷨����parse���ٶ����������£���������json�⡣
//Jackson����Ŀ��ַ��https://github.com/FasterXML/jackson�������json-lib��ܣ�Jackson��������jar�����٣������ò�������ҲҪ��Ը�Щ������Jackson������ԱȽϻ�Ծ�������ٶ�Ҳ�ȽϿ졣Jackson���ڸ������͵�jsonת��bean��������⣬һЩ����Map��List��ת���������⡣Jackson���ڸ������͵�beanת��Json��ת����json��ʽ���Ǳ�׼��Json��ʽ��
//Json-lib����Ŀ��ַ��http://json-lib.sourceforge.net/index.html����json-lib�ʼ��Ҳ��Ӧ����㷺��json�������ߣ�json-lib ���õĵط�ȷʵ�������ںܶ��������������commons-beanutils.jar��commons-collections-3.2.jar��commons-lang-2.6.jar��commons-logging-1.1.1.jar��ezmorph-1.0.6.jar�����ڸ������͵�ת����json-lib����jsonת����bean����ȱ�ݣ�����һ��������������һ�����list����map���ϣ�json-lib��json��bean��ת���ͻ�������⡣json-lib�ڹ��ܺ��������涼�����������ڻ�������������

/**
 * FastJsonUtils <br/>
 * �ص㣺�ٶ����  <br/>
 * ����:
 * 	<dependency>
 *	    <groupId>com.alibaba</groupId>
 *	    <artifactId>fastjson</artifactId>
 *	    <version>1.2.47</version>
 * 	</dependency>
 * 
 * 
 * @author fengdezhi
 */
public class FastJsonUtils {
	 
    private static final SerializeConfig config;
 
    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // ʹ�ú�json-lib���ݵ����������ʽ
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // ʹ�ú�json-lib���ݵ����������ʽ
    }
 
    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // ��������ֶ�
            SerializerFeature.WriteNullListAsEmpty, // list�ֶ����Ϊnull�����Ϊ[]��������null
            SerializerFeature.WriteNullNumberAsZero, // ��ֵ�ֶ����Ϊnull�����Ϊ0��������null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean�ֶ����Ϊnull�����Ϊfalse��������null
            SerializerFeature.WriteNullStringAsEmpty // �ַ������ֶ����Ϊnull�����Ϊ""��������null
    };
      
    /**
     * ����  to json�ַ��� <br/>
     * ת������:
     * 		1��list�ֶ����Ϊnull�����Ϊ[]��������null
     * 		2����ֵ�ֶ����Ϊnull�����Ϊ0��������null
     * 		3��Boolean�ֶ����Ϊnull�����Ϊfalse��������null
     * 		4���ַ������ֶ����Ϊnull�����Ϊ""��������null
     * @param object
     * @return
     */
    public static String object2jsonWithFeatures(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * ���� to json�ַ���
     * list | map | array
     * @param object
     * @return
     */
    public static String object2json(Object object) {
        return JSON.toJSONString(object, config);
    }
 
    /**
     * JSON�ַ��� to ����
     * @param json
     * @param clazz
     * @return
     */
    public static Object json2object(String json, Class<?> clazz) {
    	return JSONObject.parseObject(json, clazz);
    }
    
    /**
     * JSON�ַ��� to JSONObject
     * @param json
     * @return
     */
    public static JSONObject json2jsonObject(String json) {
    	return JSON.parseObject(json); 
    }
 
    /**
     * JSON�ַ��� to java Bean
     * @param text
     * @param clazz
     * @return
     */
    public static <T> T json2bean(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }
    
    /**
     * json�ַ��� to ��������
     * @param json
     * @param clazz
     * @param ts  array����
     * @return
     */
    public static <T> T[] json2array(String json, Class<T> clazz, T[] ts) {
        return JSON.parseArray(json, clazz).toArray(ts);
    }

    /**
     * json�ַ���  to ��������<br/>
     * �����鲻����չ����Ϊ��list.array()ת������
     * @param text
     * @param clazz
     * @return
     */
    public static <T> Object[] json2array(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz).toArray();
    }
 
    /**
     * json�ַ��� to List
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> json2list(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    /**
     * json�ַ��� to Map
     * @param <K>
     * @param <V>
     * @param json
     * @return
     */
    public static Map<?,?> json2map(String json) {
        return JSONObject.parseObject(json);
    }
    
    public static void main(String[] args) {
//    	String json = "{'sa':'a','asdf':'dasfs'}";
//    	Map<String,String> map = (Map<String, String>) FastJsonUtils.json2map(json);
//    	
//    	for (Entry<String, String> entry : map.entrySet()) {
//
//             System.out.println("key= " + entry.getKey() + " and value= "
//                    + entry.getValue());
//    	}
	}
}
