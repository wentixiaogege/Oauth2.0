package nz.co.jersey.google.oauth;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.ws.rs.client.Client;

import nz.co.jersey.google.oauth.config.ResourceModule;

import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jolokia.jvmagent.JvmAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.sun.jdi.Bootstrap;

public class BootStrap extends GuiceResteasyBootstrapServletContextListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

	private Client jerseyClient;

	@Override
	protected void withInjector(Injector injector) {
		jerseyClient = injector.getInstance(Client.class);
	}

	@Override
	protected List<? extends Module> getModules(final ServletContext context) {
		JvmAgent.agentmain(null);
		return Arrays.asList(new ResourceModule());
	}

	@Override
	public void contextDestroyed(final ServletContextEvent event) {
		jerseyClient.close();
		super.contextDestroyed(event);
		JvmAgent.agentmain("mode=stop");
	}

}
