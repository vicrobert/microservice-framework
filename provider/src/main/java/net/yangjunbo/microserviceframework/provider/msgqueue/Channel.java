package net.yangjunbo.microserviceframework.provider.msgqueue;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface Channel {
    /**
     * 缺省发送消息通道名称
     */
    String DEFAULT_OUTPUT = "default_output";

    /**
     * 缺省接收消息通道名称
     */
    String DEFAULT_INPUT = "default_input";

    /**
     * 告警发送消息通道名称
     */
    String ALARM_OUTPUT = "alarm_output";

    /**
     * 告警接收消息通道名称
     */
    String ALARM_INPUT = "alarm_input";

    /**
     * 缺省发送消息通道
     * @return channel 返回缺省信息发送通道
     */
    @Output(DEFAULT_OUTPUT)
    MessageChannel sendDefaultMessage();

    /**
     * 告警发送消息通道
     * @return channel 返回告警信息发送通道
     */
    @Output(ALARM_OUTPUT)
    MessageChannel sendAlarmMessage();

    /**
     * 缺省接收消息通道
     * @return channel 返回缺省信息接收通道
     */
    @Input(DEFAULT_INPUT)
    MessageChannel recieveDefaultMessage();

    /**
     * 告警接收消息通道
     * @return channel 返回告警信息接收通道
     */
    @Input(ALARM_INPUT)
    MessageChannel recieveAlarmMessage();
}
