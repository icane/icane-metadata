package es.icane.metadatos;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import es.icane.metadatos.modelo.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
public class Cliente {
    private Client jerseyClient;
    private ClientConfig clientConfig;
    private WebResource webResource;

    public Cliente(String baseUrl) {
        clientConfig = new DefaultClientConfig();
        clientConfig.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 10000);
        jerseyClient = Client.create(clientConfig);
        webResource = jerseyClient.resource(baseUrl);
    }
    
    public Nodo getNodo(String tipo, String area, String idSemantico) {
        return webResource.path(tipo).path(area).path(idSemantico).accept(MediaType.APPLICATION_XML_TYPE).get(Nodo.class);
    }
    
    public Nodo getNodo(int id) {
        return webResource.path("nodo").path(String.valueOf(id)).accept(MediaType.APPLICATION_XML_TYPE).get(Nodo.class);
    }
    
}
