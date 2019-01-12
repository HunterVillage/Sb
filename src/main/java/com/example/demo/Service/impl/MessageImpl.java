package com.example.demo.Service.impl;

import com.example.demo.Mapper.MessageMapper;
import com.example.demo.Service.message;
import com.example.demo.Vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageImpl implements message {
    //用@Auwired(会显示红但不影响运行)或者@Resource都可以把接口注入进来

  @Resource
   MessageMapper messageMapper;
    @Override
    public List<Message> selectall() {
        return messageMapper.selectall();
    }

    @Override
    public void Madda(List<Message> list) {
        messageMapper.adda(list);
    }
}
