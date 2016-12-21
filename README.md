A sample DropWizard - Hibernate - Guice - vue.js project.

To run:

```
./gradlew run
```

Then go to `http://localhost:8080/videos`. If MySQL is installed and configured with the proper database and table,
Hibernate support can be enabled in `ServerModule` by binding `Db` to a `HibernateDb` instance.

