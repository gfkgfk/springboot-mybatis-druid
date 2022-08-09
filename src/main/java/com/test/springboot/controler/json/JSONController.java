package com.test.springboot.controler.json;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.springboot.bean.JsonBean;
import com.test.springboot.bean.JsonUserBean;
import com.test.springboot.service.json.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class JsonController {

    @Autowired
    JsonService jsonService;

    @Autowired
    ObjectMapper mapper;


    @RequestMapping(value = "/getjson",method = RequestMethod.POST)
    @ResponseBody //@ResponseBody注解可以将方法返回的对象序列化成JSON
    public JsonBean getJson(){
        return jsonService.getJson();
    }


    @RequestMapping(value = "/serialization",method = RequestMethod.POST)
    @ResponseBody
    public String serialization() {
        try {
            JsonBean jsonBean = new JsonBean();
            jsonBean.setUserName("test");
            jsonBean.setBirthday(new Date());
            String str = mapper.writeValueAsString(jsonBean); //Jackson通过使用mapper的writeValueAsString方法将Java对象序列化为JSON格式字符串
            System.out.println("serialization:"+str);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("readjsonstring")
    @ResponseBody
    public String readJsonString() {
        try {
            String json = "{\"name\":\"mrbird\",\"age\":26}";
            JsonNode node = this.mapper.readTree(json);
            String name = node.get("name").asText();
            int age = node.get("age").asInt();
            System.out.println(name + " " + age);


            //解析多级
            String json2 = "{\"name\":\"mrbird\",\"hobby\":{\"first\":\"sleep\",\"second\":\"eat\"}}";;
            JsonNode node2 = this.mapper.readTree(json2);
            JsonNode hobby2 = node2.get("hobby");
            String first = hobby2.get("first").asText();
            System.out.println(first);

            return "name:"+name + " age:" + age +" first:"+first;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @RequestMapping("/readjsonasobject")
    @ResponseBody
    public String readJsonAsObject() {
        try {
            String json = "{\"userName\":\"mrbird\",\"age\":26}";
            JsonBean jsonBean = mapper.readValue(json, JsonBean.class);
            String name = jsonBean.getUserName();
            int age = jsonBean.getAge();
            return name + " " + age;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("/jsonview")
    @ResponseBody
    @JsonView(JsonUserBean.UserNameView.class)
    public JsonUserBean getJsonView(){
        JsonUserBean jsonUserBean = new JsonUserBean();
        jsonUserBean.setAge(11);
        jsonUserBean.setUserName("gogogo");
        jsonUserBean.setPassword("nnnn");
        jsonUserBean.setBirthday(new Date());
        return jsonUserBean;
    }


    //测试参数:[{"userName":"mrbird","info":"nikll"},{"userName":"scott","info":"nikll2"}]
    @RequestMapping("/updatejson")
    @ResponseBody
    public int updateJson(@RequestBody List<JsonBean> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        return list.size();
    }
}
