package configuration;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

public class CORSResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        MultivaluedMap<String, Object> headers = containerResponseContext.getHeaders();

        headers.add("Access-Control-Allow-Origin", "*");
//        headers.add("Access-Control-Allow-Origin", "http://localhost:4200");

        //headers.add("Access-Control-Allow-Origin", "http://podcastpedia.org"); //allows CORS requests only coming from podcastpedia.org
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, HEAD");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia, Access-Control-Allow-Headers, Authorization");
    }
}
