package resources.websockets;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.Path;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value="/websocket/kweet",
                decoders = MessageDecoder.class,
                encoders = MessageEncoder.class)
public class KweetResource {

    private Session session;
    private static Set<KweetResource>  kweetEndpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, String>  kweets = new HashMap<>();


    @OnOpen
    public void onOpen(Session session, @PathParam("kweet") String kweet)
    {
        this.session = session;
        System.out.println("test?? ");
        kweetEndpoints.add(this);
        kweets.put(session.getId(), kweet);

        Message message = new Message();
        message.setFrom(kweet);
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
        message.setFrom(kweets.get(session.getId()));
        try {
            broadcast(message);
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {

        kweetEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(kweets.get(session.getId()));
        message.setContent("Disconnected!");
        try {
            broadcast(message);
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }


    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
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
