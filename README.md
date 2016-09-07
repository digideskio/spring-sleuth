##Spring Sleuth

###About

Simple app to read zipkin tracing headers.

###Instructions to deploy the app to CloudFoundry

- Install [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
- Run the following commands

```
git clone https://github.com/cf-routing/spring-sleuth
cd spring-sleuth
brew install maven
mvn compile
mvn package
```

- Change the `manifest.yml` to add the domain on which app will be deployed.
- Target the CF deployment and push the app with following commands

```
cf api api.domain.com
cf login
cf target -o spring -s spring
cf push spring-sleuth
```

- Run `curl spring-sleuth-app-route.domain.com` should give similar output

```
current span: [Trace: ae53b011aa66f5d9, Span: 4b3f714133070007, exportable=true]
 parents: [9f14eac1020280e9
```
