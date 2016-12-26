/**
 * Project Name:cici
 * File Name:sss.java
 * Package Name:com.cn.th.mq.common.producer
 * Date:2016年12月26日上午10:59:03
 * Copyright (c) 2016, All Rights Reserved.
 *
*/

package com.cn.th.mq.common.producer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.Test;
/**
 * 事务的生产者.<br>
 * 测试发送正常和发送异常.
 * @author th
 *
 */
public class TransactedProducer {
    /** JNDI name for ConnectionFactory */
    static final String CONNECTION_FACTORY_JNDI_NAME = "ConnectionFactory";
    /** JNDI name for Queue Destination (use for PTP Mode) */
    static final String QUEUE_JNDI_NAME = "exampleQueue";
    
    /** 是否故意异常 */
    boolean intentional = false;
    
    /**
     * 测试发送正常.
     */
    @Test
    public void sendNormal() {
        this.intentional = false;
        send(QUEUE_JNDI_NAME);
    }
    
    /**
     * 测试发送异常.
     */
    @Test
    public void sendIntentional() {
        this.intentional = true;
        send(QUEUE_JNDI_NAME);
    }
    
    /**
     * 是否故意抛出异常.<br>
     * 如果不抛出异常，会有3条消息入队；否则没有消息入队.
     * @return
     */
    private boolean isIntentional() {
        return intentional;
    }
    
    /**
     * 发送到指定的目的地.
     * 
     * @param destJndiName
     *            目的地的JNDI name.
     */
    private void send(String destJndiName) {
        Context jndiContext = null;
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageProducer producer = null;
        // create a JNDI API IntialContext object
        try {
            jndiContext = new InitialContext();
        } catch (NamingException e) {
            System.out.println("Could not create JNDI Context:"
                    + e.getMessage());
            System.exit(1);
        }
        // look up ConnectionFactory and Destination
        try {
            connectionFactory = (ConnectionFactory) jndiContext
                    .lookup(CONNECTION_FACTORY_JNDI_NAME);
            destination = (Destination) jndiContext.lookup(destJndiName);
        } catch (NamingException e) {
            System.out.println("JNDI look up failed:" + e.getMessage());
            System.exit(1);
        }
        // send Messages and finally release the resources.
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(Boolean.TRUE,
                    Session.SESSION_TRANSACTED);
            producer = session.createProducer(destination);
            
            TextMessage message = session.createTextMessage();
            for (int i = 0; i < 3; i++) {
                message.setText(String.format("This is the %dth message.",
                        i + 1));
                producer.send(message);
            }
            
            if (isIntentional()) {
                throw new JMSException("这是一个故意抛出的异常。");
            }
            
            session.commit(); // 在最后提交
        } catch (JMSException e) {
            try {
                session.rollback();
            } catch (JMSException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (session != null)
                    session.close();
                if (connection != null)
                    connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
