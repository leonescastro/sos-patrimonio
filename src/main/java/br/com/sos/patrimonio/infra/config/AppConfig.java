package br.com.sos.patrimonio.infra.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        servers = {
                @Server(
                        description = "Local",
                        url = "http://localhost:8080"
                ),
        },
        info = @Info(
                title="SOS - Microsservico Patrimônio",
                version = "1.0.0"
        )
)
public class AppConfig extends Application {}
