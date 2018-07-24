package pers.feng.baseUtils.json;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;


//Gson（项目地址：https://github.com/google/gson）。Gson是目前功能最全的Json解析神器，Gson当初是为因应Google公司内部需求而由Google自行研发而来，但自从在2008年五月公开发布第一版后已被许多公司或用户应用。Gson的应用主要为toJson与fromJson两个转换函数，无依赖，不需要例外额外的jar，能够直接跑在JDK上。而在使用这种对象转换之前需先创建好对象的类型以及其成员才能成功的将JSON字符串成功转换成相对应的对象。类里面只要有get和set方法，Gson完全可以将复杂类型的json到bean或bean到json的转换，是JSON解析的神器。
//FastJson（项目地址：https://github.com/alibaba/fastjson）。Fastjson是一个Java语言编写的高性能的JSON处理器,由阿里巴巴公司开发。无依赖，不需要例外额外的jar，能够直接跑在JDK上。FastJson在复杂类型的Bean转换Json上会出现一些问题，可能会出现引用的类型，导致Json转换出错，需要制定引用。FastJson采用独创的算法，将parse的速度提升到极致，超过所有json库。
//Jackson（项目地址：https://github.com/FasterXML/jackson）。相比json-lib框架，Jackson所依赖的jar包较少，简单易用并且性能也要相对高些。而且Jackson社区相对比较活跃，更新速度也比较快。Jackson对于复杂类型的json转换bean会出现问题，一些集合Map，List的转换出现问题。Jackson对于复杂类型的bean转换Json，转换的json格式不是标准的Json格式。
//Json-lib（项目地址：http://json-lib.sourceforge.net/index.html）。json-lib最开始的也是应用最广泛的json解析工具，json-lib 不好的地方确实是依赖于很多第三方包，包括commons-beanutils.jar，commons-collections-3.2.jar，commons-lang-2.6.jar，commons-logging-1.1.1.jar，ezmorph-1.0.6.jar，对于复杂类型的转换，json-lib对于json转换成bean还有缺陷，比如一个类里面会出现另一个类的list或者map集合，json-lib从json到bean的转换就会出现问题。json-lib在功能和性能上面都不能满足现在互联网化的需求。

/**
 * FastJsonUtils <br/>
 * 特点：速度最快  <br/>
 * 依赖:
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
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }
 
    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };
      
    /**
     * 对象  to json字符串 <br/>
     * 转换规则:
     * 		1、list字段如果为null，输出为[]，而不是null
     * 		2、数值字段如果为null，输出为0，而不是null
     * 		3、Boolean字段如果为null，输出为false，而不是null
     * 		4、字符类型字段如果为null，输出为""，而不是null
     * @param object
     * @return
     */
    public static String object2jsonWithFeatures(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * 对象 to json字符串
     * list | map | array
     * @param object
     * @return
     */
    public static String object2json(Object object) {
        return JSON.toJSONString(object, config);
    }
 
    /**
     * JSON字符串 to 对象
     * @param json
     * @param clazz
     * @return
     */
    public static Object json2object(String json, Class<?> clazz) {
    	return JSONObject.parseObject(json, clazz);
    }
    
    /**
     * JSON字符串 to JSONObject
     * @param json
     * @return
     */
    public static JSONObject json2jsonObject(String json) {
    	return JSON.parseObject(json); 
    }
 
    /**
     * JSON字符串 to java Bean
     * @param text
     * @param clazz
     * @return
     */
    public static <T> T json2bean(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }
    
    /**
     * json字符串 to 对象数组
     * @param json
     * @param clazz
     * @param ts  array容器
     * @return
     */
    public static <T> T[] json2array(String json, Class<T> clazz, T[] ts) {
        return JSON.parseArray(json, clazz).toArray(ts);
    }

    /**
     * json字符串  to 对象数组<br/>
     * 该数组不可扩展，因为由list.array()转换而来
     * @param text
     * @param clazz
     * @return
     */
    public static <T> Object[] json2array(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz).toArray();
    }
 
    /**
     * json字符串 to List
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> json2list(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    /**
     * json字符串 to Map
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
