package tn.esprit.servicereservation.config;


import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class KeycloakConfig {
    @Bean
    public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
    static Keycloak keycloak=null;
    final static String serverUrl = "http://localhost:8180/auth/";
    public final static String realm = "springbootproject";
    public final static String clientId = "ms-service-reservation";
    final static String clientSecret = "f139ad14-5c10-46f7-97d2-d41f376b53bd";
    final static String userName = "admin";
    final static String password = "admin";

    public KeycloakConfig() {
    }

    @Bean
    public static Keycloak getInstance() {
        if (keycloak == null) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilder()

                            .connectionPoolSize(10)

                            .build())
                    .build();
        }
        return keycloak;
    }
}
