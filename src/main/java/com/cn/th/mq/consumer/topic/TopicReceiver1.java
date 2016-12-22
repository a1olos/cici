/**
 * Project Name:cici
 * File Name:dd.java
 * Package Name:com.cn.th.mq.consumer.topic
 * Date:2016年12月22日下午4:56:40
 * Copyright (c) 2016, All Rights Reserved.
 *
*/

package com.cn.th.mq.consumer.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * ClassName:dd <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年12月22日 下午4:56:40 <br/>
 * @author   TH
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Component
public class TopicReceiver1 implements MessageListener{


	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("TopicReceiver1接收到消息:"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
