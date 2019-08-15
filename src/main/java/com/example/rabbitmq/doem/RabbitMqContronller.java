package com.example.rabbitmq.doem;

import com.example.rabbitmq.config.Send;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title
 * @Autor zxf
 * @Date 2019/8/15
 */
@RestController
public class RabbitMqContronller {

    private final Send send;

    @Autowired
    public RabbitMqContronller(Send send) {
        this.send = send;
    }

    @ApiOperation(value = "简单模式发送json消息",notes = "简单模式发送json消息" )
    @ApiImplicitParam(name="json",value="消息json",required=true,dataType="String",paramType="header",defaultValue="application/json")
    @GetMapping("/simplSend")
    public String simplSend(@RequestParam(value = "json", required = false) String json){
        try {
            this.send.simplSend(json);
            return "succeed";
        } catch (Exception e) {
            e.printStackTrace();
            return  "error";
        }
    }

    @ApiOperation(value = "订阅模式发送json消息",notes = "订阅模式发送json消息" )
    @ApiImplicitParam(name="json",value="消息json",required=true,dataType="String",paramType="header",defaultValue="application/json")
    @GetMapping("/routeSend")
    public String routeSend(@RequestParam(value = "json", required = false) String json){
        try {
            this.send.routeSend(json);
            return "succeed";
        } catch (Exception e) {
            e.printStackTrace();
            return  "error";
        }
    }

    @ApiOperation(value = "路由模式发送json消息",notes = "路由模式发送json消息" )
    @ApiImplicitParams({
            @ApiImplicitParam(name="json",value="消息json",required=true,dataType="String",paramType="header",defaultValue="application/json"),
            @ApiImplicitParam(name="routingKey",value="key",required=true,dataType="String",paramType="header",defaultValue="application/json")})
    @GetMapping("/routingSend")
    public String routingSend(
            @RequestParam(value = "json", required = false) String json,
            @RequestParam(value ="routingKey", required = false) String routingKey){
        try {
            this.send.routingSend(routingKey,json);
            return "succeed";
        } catch (Exception e) {
            e.printStackTrace();
            return  "error";
        }
    }

    @ApiOperation(value = "主题模式发送json消息",notes = "主题模式发送json消息" )
    @ApiImplicitParams({
            @ApiImplicitParam(name="json",value="消息json",required=true,dataType="String",paramType="header",defaultValue="application/json"),
            @ApiImplicitParam(name="routingKey",value="key",required=true,dataType="String",paramType="header",defaultValue="application/json")})
    @GetMapping("/topicSend")
    public String topicSend(
            @RequestParam(value = "json", required = false) String json,
            @RequestParam(value ="routingKey", required = false) String routingKey){
        try {
            this.send.topicSend(routingKey,json);
            return "succeed";
        } catch (Exception e) {
            e.printStackTrace();
            return  "error";
        }
    }

}
