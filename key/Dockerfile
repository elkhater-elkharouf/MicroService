# Utilisation d'une image de base Java 8
FROM openjdk:8

EXPOSE 8180
# Copiez le répertoire de Keycloak dans l'image
COPY keycloak-12.0.0 /opt/keycloak

# Définissez le répertoire de travail
WORKDIR /opt/keycloak

# Exécutez Keycloak avec le port personnalisé
CMD ["/opt/keycloak/bin/standalone.sh", "-b", "0.0.0.0"]