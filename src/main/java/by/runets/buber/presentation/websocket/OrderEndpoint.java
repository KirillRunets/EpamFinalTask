package by.runets.buber.presentation.websocket;

import by.runets.buber.domain.entity.User;
import by.runets.buber.infrastructure.constant.RequestParameter;
import by.runets.buber.infrastructure.constant.UserRoleType;
import by.runets.buber.presentation.websocket.configurator.GetHttpSessionConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ServerEndpoint(value = "/buber/order/{command}", decoders = MessageDecoder.class, encoders = MessageEncoder.class, configurator = GetHttpSessionConfigurator.class)
public class OrderEndpoint {
    private final static Logger LOGGER = LogManager.getLogger(OrderEndpoint.class);
    private Session session;
    private static final Set<OrderEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    private static Map<String, User> users;
    private static Lock lock = new ReentrantLock();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("command") String command) throws IOException, EncodeException {
        users = new HashMap<>();
        chatEndpoints.add(this);
        this.session = session;
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        User user = (User) httpSession.getAttribute(UserRoleType.USER);
        users.put(session.getId(), user);
      /*  Message message = new Message();
        message.setFrom(username);
        message.setContent("Connected!");
        broadcast(message);*/
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        /*message.setFrom(users.get(session.getId()));*/
        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        chatEndpoints.remove(this);
        Message message = new Message();
        /*message.setFrom(users.get(session.getId()));*/
        message.setContent("Disconnected!");
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private static void broadcast(Message message) throws IOException, EncodeException {
        chatEndpoints.forEach(endpoint -> {
            lock.lock();
            try {
                endpoint.session.getBasicRemote()
                        .sendObject(message);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
            lock.unlock();
        });
    }
}
