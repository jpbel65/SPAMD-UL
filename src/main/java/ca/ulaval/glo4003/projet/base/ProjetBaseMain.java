package ca.ulaval.glo4003.projet.base;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * RESTApi setup without using DI or spring
 */
@SuppressWarnings("all")
public class ProjetBaseMain {
    public static final int PORT = 8080;
    public static final String CONTEXT_PATH = "/api/";

    public static void main(String[] args) {
        Server server = new Server(PORT);
        ServletContextHandler contextHandler = new ServletContextHandler(server, CONTEXT_PATH, ServletContextHandler.SESSIONS);
        ResourceConfig resourceConfig = new ProjectConfig();

        ServletContainer container = new ServletContainer(resourceConfig);
        ServletHolder servletHolder = new ServletHolder(container);

        contextHandler.addServlet(servletHolder, "/*");

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.destroy();
        }
    }
}
