package resources.websockets;

import javax.ejb.Singleton;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;

@ServerEndpoint(value="/websocket/kweet",
                decoders = MessageDecoder.class,
                encoders = MessageEncoder.class)
public class WebsocketResource {

    private Session session;
//    private final Set<Session> sessions = new HashSet<>();
    private static Set<WebsocketResource>  kweetEndpoints = new CopyOnWriteArraySet<>();
//    private static HashMap<String, String>  kweets = new HashMap<>();
    private static HashMap<String, String> users = new HashMap<>();
    Logger logger = Logger.getLogger(getClass().getName());


    @OnOpen
    public void onOpen(Session session, @PathParam("user") String userId)
    {
        this.session = session;
        kweetEndpoints.add(this);
        logger.info("testMessage");
//        sessions.add(session);
        users.put(session.getId(), userId);
        System.out.println("New user connection: "+ userId);

        Message message = new Message();
        message.setFrom(userId);
        message.setContent("Connected!");
        try {
            broadcast(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }

    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException {
        System.out.println("reached onmessage");
        logger.info("Reached on message yay");
        logger.info(message.toString());


        message.setFrom(users.get(session.getId()));
        System.out.println(message);
        try {
            broadcast(message);
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("reached onclose. user is leaving");

        kweetEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");
        try {
            broadcast(message);
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }


    @OnError
    public void onError(Session session, Throwable throwable) {
        Message message = new Message();
        message.setFrom("Server");
        message.setContent(throwable.toString());
        try {
            broadcast(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

    public void send(String test){
        logger.info(test);

    }

    private static void broadcast(Message message)
            throws IOException, EncodeException {

        kweetEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote().
                            sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
