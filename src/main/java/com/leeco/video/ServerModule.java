package com.leeco.video;

/**
 * @author Cedric Beust <cedric@beust.com>
 * @since 12/12/2016
 */

import com.google.inject.Binder;
import com.google.inject.Module;
import com.leeco.video.api.Video;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(IClock.class).to(DummyClock.class);

        // Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Hibernate
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Video.class)
                .buildSessionFactory(registry);

        Session session = factory.getCurrentSession();
        binder.bind(Session.class).toInstance(session);

        GithubService service = retrofit.create(GithubService.class);
        binder.bind(GithubService.class).toInstance(service);
    }

    static class DummyClock implements IClock {

        @Override
        public String now() {
            return "The time is now";
        }
    }

}
