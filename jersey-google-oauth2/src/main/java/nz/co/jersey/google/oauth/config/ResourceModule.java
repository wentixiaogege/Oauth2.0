package nz.co.jersey.google.oauth.config;

import nz.co.jersey.google.oauth.resource.AuthorizationResource;
import nz.co.jersey.google.oauth.resource.SetupResource;
import nz.co.jersey.google.oauth.resource.TaskResource;

import com.google.inject.AbstractModule;

public class ResourceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AuthorizationResource.class);
		bind(SetupResource.class);
		bind(TaskResource.class);
	}
}
