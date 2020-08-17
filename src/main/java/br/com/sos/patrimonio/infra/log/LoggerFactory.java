package br.com.sos.patrimonio.infra.log;

import org.jboss.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerFactory {

    @Produces
    public Logger createLogger(InjectionPoint ip){
        return Logger.getLogger(ip.getMember().getDeclaringClass());
    }
}
