package com.bookbecho.infrastructure.core.config;

import com.bookbecho.users.api.UsersApiResource;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;


@Configuration
public class JerseyConfig{

    private static final Logger LOG = LoggerFactory.getLogger(JerseyConfig.class);

    JerseyConfig() {
//        register(org.glassfish.jersey.media.multipart.MultiPartFeature.class);
//        property(ServerProperties.WADL_FEATURE_DISABLE, true);
    }

    @Autowired
    ApplicationContext appCtx;

    @Bean
    public ResourceConfig resourceConfig() {
        ResourceConfig config = new ResourceConfig();
        appCtx.getBeansWithAnnotation(Path.class).values().forEach(component -> config.register(component.getClass()));
        return config;
    }

}
