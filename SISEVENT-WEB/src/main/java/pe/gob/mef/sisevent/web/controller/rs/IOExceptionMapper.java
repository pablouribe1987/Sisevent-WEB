package pe.gob.mef.sisevent.web.controller.rs;

import java.io.IOException;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class IOExceptionMapper implements ExceptionMapper<IOException> {

    @Override
    public Response toResponse(IOException ex) {
        System.out.println("toResponse IOExceptionMapper");
        return Response.status(404).entity(ex.getMessage()).type("text/plain").build();
    }
}
