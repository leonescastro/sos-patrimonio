package br.com.sos.patrimonio.infra.filter;

import br.com.sos.patrimonio.infra.exception.Erro;
import org.apache.commons.lang.exception.ExceptionUtils;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionsFilter implements ExceptionMapper<Exception> {

    @Inject
    org.jboss.logging.Logger logger;


    @Override
    public Response toResponse(Exception e) {
        logger.error("Error with exception log", e);

        int code = 500;

        if (e instanceof WebApplicationException) {
            code = ((WebApplicationException) e).getResponse().getStatus();
        }

        return Response.status(code)
                .entity(Erro.builder().code(String.valueOf(code)).message(ExceptionUtils.getRootCauseMessage(e)).build())
                .build();
    }
}
