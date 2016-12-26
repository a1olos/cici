/**
 * Project Name:cici
 * File Name:sss.java
 * Package Name:com.cn.test
 * Date:2016年12月26日上午11:14:43
 * Copyright (c) 2016, All Rights Reserved.
 *
*/

package com.cn.test;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.Test;
/**
 * 事务的接收者.<br>
 * 测试接收正常和接收异常.
 * @author th
 *
 */
public class TransactedConsumer {
    /** JNDI name for ConnectionFactory */
    static final String CONNECTION_FACTORY_JNDI_NAME = "ConnectionFactory";
    /** JNDI name for Queue Destination (use for PTP Mode) */
    static final String QUEUE_JNDI_NAME = "exampleQueue";
    
    /** 是否故意异常 */
    boolean intentional = false;
    
    @Test
    public void receiveNormal() {
        intentional = false;
        receive(QUEUE_JNDI_NAME);
    }
    
    @Test
    public void receiveIntentional() {
        intentional = true;
        receive(QUEUE_JNDI_NAME);
    }
    
    /**
     * 是否故意抛出异常，以检查消息的出队情况.
     * @return
     */
    private boolean isIntentional() {
        return intentional;
    }
    
    /**
     * 接收消息.<br>
     * 
     * @param destJndiName
     *            目的地的JNDI name.
     */
    private void receive(String destJndiName) {
        Context jndiContext = null;
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageConsumer consumer = null;
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
        // receive Messages and finally release the resources.
        try {
            connection = connectionFactory.createConnection();
            connection.start(); // connection should be called in
                                // receiver-client
            session = connection.createSession(Boolean.TRUE,
                    Session.SESSION_TRANSACTED);
            consumer = session.createConsumer(destination);
            
            long timeout = 5 * 1000; // timeout:5 seconds
            for (Message message = consumer.receive(timeout); message != null; message = consumer.receive(timeout)) {
                String text = ((TextMessage) message).getText();
                System.out.println(text);
            }
            
            if (isIntentional()) {
                throw new JMSException("这是一个故意抛出的异常。");
            }
            
            session.commit();
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
