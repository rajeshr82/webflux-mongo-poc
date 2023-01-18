package com.rajesh.poc.controller;

import com.rajesh.poc.dto.EmployeeDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeMsgListnerController {
    List<String> message = new ArrayList<>();
    EmployeeDto empFromTopic = null;
    @KafkaListener(groupId = "employeeMsg",topics = "employeetopic",containerFactory = "kafkaListenerContainerFactory")
    public List<String> getMessage(String data){
        message.add(data);
        return message;
    }

    @GetMapping("/consumemessage")
    public List<String> consumeMessage(){
        return message;
    }

    @KafkaListener(groupId = "employeeUser",topics = "employee",containerFactory = "employeeKafkaListenerContainerFactory")
    public EmployeeDto getMessage(EmployeeDto employeeDto){
        empFromTopic = employeeDto;
        return empFromTopic;
    }

    @GetMapping("/consumenjsonmessage")
    public EmployeeDto consumeJsonMessage(){
        return empFromTopic;
    }

}
