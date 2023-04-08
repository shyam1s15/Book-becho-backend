package com.bookbecho.infrastructure.core.config;

import com.bookbecho.users.api.UsersApiResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

@Configuration
@ApplicationPath("/book_becho")
public class JerseyConfig{

    private static final Logger LOG = LoggerFactory.getLogger(JerseyConfig.class);

    JerseyConfig() {
//        register(org.glassfish.jersey.media.multipart.MultiPartFeature.class);
//        property(ServerProperties.WADL_FEATURE_DISABLE, true);
    }

    @Autowired
    ApplicationContext appCtx;

//    @PostConstruct
//    public void setup() {
//        appCtx.getBeansWithAnnotation(Path.class).values().forEach(component -> register(component.getClass()));
//
//        appCtx.getBeansWithAnnotation(Provider.class).values().forEach(this::register);
//    }

    @Bean
    public ResourceConfig resourceConfig() {
        ResourceConfig config = new ResourceConfig();
        appCtx.getBeansWithAnnotation(Path.class).values().forEach(component -> config.register(component.getClass()));
        config.register(org.glassfish.jersey.media.multipart.MultiPartFeature.class);
        config.property(ServerProperties.WADL_FEATURE_DISABLE, true);
        return config;
    }

}
