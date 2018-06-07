package util;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json 工具类
 *
 */
public class JsonUtil {

    /**
     * 转换json 字符串为map
     * @param jsonStr
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String,Object> jsonToMap(String jsonStr){
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = om.readValue(jsonStr, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将一个map转换成JSON对象
     * @param param
     * @return
     */
    public static String mapToJson(Map<String,Object> m) {
        ObjectMapper om = new ObjectMapper();

        String json="";
        try {
            json = om.writeValueAsString(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    /**
     * 将一个list转换成json串，转换后格式
     * [{key:'value'},{key:'value'},{key:'value'}...]
     * @param param
     * @return
     */

    public static String listToJson(List<Map<String,Object>> list) {
        ObjectMapper om = new ObjectMapper();
        String json = "";
        try {
            json = om.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //System.out.println(json);
        return json;
    }

    /**
     * 将一个array类型的json串转换成list
     * @param jsonStr
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String,Object>> jsonToList(String jsonStr){
        ObjectMapper om = new ObjectMapper();
        List<Map<String, Object>> list = null;
        try {
            list = om.readValue(jsonStr, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}