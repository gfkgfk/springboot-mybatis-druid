package com.test.springboot.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//如果传输的是二进制内容，则可以继承BinaryWebSocketHandler，更多信息可以自行查看WebSocketHandler的子类。

@Component
public class MyStringWebSocketHandler extends TextWebSocketHandler {


    @Override //和客户端链接成功的时候触发该方法
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("--------------websocket连接建立成功--------------");
        super.afterConnectionEstablished(session);
    }

    @Override //和客户端连接失败的时候触发该方法
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("--------------websocket连接建立失败--------------");
        super.handleTransportError(session, exception);
    }

    @Override //和客户端建立连接后，处理客户端发送的请求。
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("--------------处理websocket消息--------------");
        // 获取到客户端发送过来的消息
        String receiveMessage = message.getPayload();
        System.out.println("--------------消息:"+receiveMessage);
        // 发送消息给客户端
        session.sendMessage(new TextMessage(fakeAi(receiveMessage)));
        // 关闭连接
        // session.close(CloseStatus.NORMAL);
        super.handleTextMessage(session, message);
    }


    @Override //和客户端断开连接的时候触发该方法；
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("--------------websocket连接断开--------------");
        super.afterConnectionClosed(session, status);
    }


    private static String fakeAi(String input) {
        if (input == null || "".equals(input)) {
            return "你说什么？没听清︎";
        }
        return input.replace('你', '我')
                .replace("吗", "")
                .replace('?', '!')
                .replace('？', '！');
    }
}
