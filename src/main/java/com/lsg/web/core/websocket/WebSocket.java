package com.lsg.web.core.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lsg
 * @version 1.0
 * @date 2018/8/21
 * @since 1.0
 */
@Slf4j
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocket {

    // 记录websocket连接数量
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private static CopyOnWriteArraySet<Session> webSocketSet = new CopyOnWriteArraySet();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        webSocketSet.add(session);
        onlineCount.incrementAndGet();
        log.info("有新连接加入, 当前在线人数:{}", getOnlineCount());
        broadcastMessage(getOnlineCount(), webSocketSet);
    }

    @OnClose
    public void onClose() {
        onlineCount.decrementAndGet();
        log.info("有链接关闭, 当前在线人数:{}", getOnlineCount());
        broadcastMessage(getOnlineCount(), webSocketSet);
    }
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("{}来自客户端的消息:{}", session.getId(), message);
        sendMessage(message, session);
    }

    public String getOnlineCount() {
        return Integer.toString(onlineCount.get());
    }

    public static void sendMessage(String message, Session session){
        try {
            session.getAsyncRemote().sendText(message);
        } catch (Exception e) {
            log.error("websocket-->向客户端发送数据发生异常", e);
        }
    }

    public static void broadcastMessage(String message, Set<Session> webSocketSet){
        for(Session session: webSocketSet) {
            if (session.isOpen()) {
                sendMessage(message, session);
            } else {
                webSocketSet.remove(session);
            }
        }
    }
}
