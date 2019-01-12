package com.example.demo.Web;

import com.alibaba.fastjson.JSON;
import com.example.demo.Service.message;
import com.example.demo.Vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmailController {

    @Autowired
    message me;

    //查询数据库数据返回json
    @GetMapping("/select")
    public List<Message> selectall(){
        //List<Message> list=new ArrayList<>();


        return me.selectall();
    }
    //准备将查出来的数据通过fastjson转为String放入redis中,然后在转为List并返回
    @GetMapping("/addredis")
    public List<Message> test(){
        //连接本地redis
        Jedis jedis=new Jedis("localhost");
        //测试连接是否成功
        System.out.println("连接信息："+jedis.ping());
        System.out.println(jedis);
        List<Message> list=null;
        if(jedis!=null){
            jedis.set("messagelist", JSON.toJSONString(me.selectall()));
        }
        List<Message> list1=JSON.parseArray(jedis.get("messagelist"),Message.class);
        return list1;
    }


    //批量插入list
    @PostMapping("/add")
    public void add(){

        List<Message> list=new ArrayList();

        Message m1=new Message();
        m1.setEmail("xxxxx@qq.com");
        m1.setMessage("今天面试了一家游戏公司");
        m1.setPassword("nnn");

        Message m2=new Message();
        m2.setEmail("mmmm@qq.com");
        m2.setMessage("英雄联盟");
        m2.setPassword("157257aaa");


        list.add(m1);
        list.add(m2);

        me.Madda(list);


    }

}
