package com.leeco.video;

/**
 * @author Cedric Beust <cedric@beust.com>
 * @since 12/12/2016
 */

import com.google.common.collect.ImmutableList;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.leeco.video.api.Video;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

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

//        binder.bind(Db.class).toInstance(new HibernateDb(factory));
        binder.bind(Db.class).toInstance(new MockDb());

        GithubService service = retrofit.create(GithubService.class);
        binder.bind(GithubService.class).toInstance(service);
    }

    static class HibernateDb implements Db {
        private final SessionFactory factory;

        public HibernateDb(SessionFactory factory) {
            this.factory = factory;
        }

        @Override
        public List<Video> getVideos() {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            List result = session.createQuery("from videos").list();
            session.close();
            return result;
        }
    }

    static class MockDb implements Db {
        @Override
        public List<Video> getVideos() {
            return ImmutableList.of(
                    new Video("Avatar (really a mock)", 2009)
            );
        }
    }

    static class DummyClock implements IClock {

        @Override
        public String now() {
            return "The time is now";
        }
    }

}
