package config;

import javax.ws.rs.ApplicationPath;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import service.FoodRestService;

@ApplicationPath("rs")
public class ApplicationConfig extends ResourceConfig {
	
	/**
	 * Constructor
	 */
	public ApplicationConfig() {
		super(FoodRestService.class);
		this.packages("service");
		this.register(MOXyJsonProvider.class);
		this.register(RolesAllowedDynamicFeature.class);
	}
}
