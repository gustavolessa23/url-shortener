# URL Shortener API

This project uses Quarkus, PostgreSQL DB and Lombok.

When using Panache ORM, if an entity extends `PanacheEntity`, when writing its fields as public, Quarkus will automatically convert them to private and create accessors and mutator. All direct calls are transformed into getters/setters' calls also. It also sets the ID automatically.

However, when two entities (`UrlEntry` and `LogEntry`) were using this feature, the ID was sequentially being assigned considering both, meaning that the system would have half of the slots for URLs (for example, ID with even numbers weren't being assigned to UrlEntry).

Therefore, as a workaround, LogEntry extended `PanacheEntityBase` and its Id was generated as usual.

Moreover, there is an incompatibility with Quarkus, Lombok and Java 8. Apparently, updating to Java 11 solves the issue, but this wasn't attempted. Getters and Setters were manually created for LogEntry class.


If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .


## Endpoints

`/api/v1`  POST request with `url` as body param to shorten a new URL.

`/api/v1/{code}` GET request with `code` as request param to redirect to original URL from shortened code. 


## Example for POST

> curl --location --request POST '0.0.0.0:8080/api/v1' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'url=https://github.com/gustavolessa23/url-shortener'


## Deployment to Heroku
This project was deployed to Heroku and is accessible using the following URL:
```
http://gurusl.herokuapp.com/api/v1
```


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application is packageable using `./mvnw package`.
It produces the executable `url-shortener-1.0.0-SNAPSHOT-runner.jar` file in `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/url-shortener-1.0.0-SNAPSHOT-runner.jar`.


## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or you can use Docker to build the native executable using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your binary: `./target/url-shortener-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image-guide .