package centralni.modul.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpsConfiguration {

  /*@Bean
  public ServletWebServerFactory servletContainer() {
    TomcatServletWebServerFactory tomcat =
        new TomcatServletWebServerFactory() {
          @Override
          protected void postProcessContext(Context context) {
            SecurityConstraint securityConstraint = new SecurityConstraint();
            securityConstraint.setUserConstraint("CONFIDENTIAL");
            SecurityCollection collection = new SecurityCollection();
            collection.addPattern("/*");
            securityConstraint.addCollection(collection);
            context.addConstraint(securityConstraint);
          }
        };
    tomcat.addAdditionalTomcatConnectors(redirectConnector());
    return tomcat;
  }

  private Connector redirectConnector() {
    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    connector.setScheme("http");
    connector.setPort(8444);
    connector.setSecure(false);
    connector.setRedirectPort(8443);
    return connector;
  }*/
}
