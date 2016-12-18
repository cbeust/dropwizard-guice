package com.leeco.video;

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.leeco.video.health.TemplateHealthCheck;
import com.leeco.video.resources.VideoListApi;
import com.leeco.video.resources.VideoListResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

/**
 * @author Cedric Beust <cedric@beust.com>
 * @since 12/12/2016
 */
public class MainApplication extends Application<MainConfiguration> {
    public static void main(final String[] args) throws Exception {
        new MainApplication().run(args);
    }

    @Override
    public String getName() {
        return "HelloWorld";
    }

    @Override
    public void run(MainConfiguration configuration, Environment environment) {
        TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(new VideoListResource());
        environment.jersey().register(new VideoListApi());
    }

    @Override
    public void initialize(Bootstrap<MainConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<>());
        GuiceBundle<MainConfiguration> guiceBundle = GuiceBundle.<MainConfiguration>newBuilder()
                .addModule(new ServerModule())
                .setConfigClass(MainConfiguration.class)
                .build();

        bootstrap.addBundle(guiceBundle);
        bootstrap.addBundle(new AssetsBundle());
    }
}