/**
 * Project Name:cici
 * File Name:ww.java
 * Package Name:com.cn.th.mq
 * Date:2016年12月22日下午4:54:50
 * Copyright (c) 2016, All Rights Reserved.
 *
*/

package com.cn.th.mq.consumer.queue;
/**
 * ClassName:ww <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年12月22日 下午4:54:50 <br/>
 * @author   TH
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * 
 * @author liang
 * @description  队列消息监听器
 * 
 */
@Component
public class QueueReceiver1 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("QueueReceiver1接收到消息:"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
