package net.yangjunbo.microserviceframework.provider.msgqueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    @Autowired
    private Channel channel;

    /**
     * 消息发送到告警通道：告警通道对应告警主题
     * @param message
     */
    public void sendToAlarmChannel(String message){
        channel.sendAlarmMessage().send(MessageBuilder.withPayload(message).build());
    }
}
