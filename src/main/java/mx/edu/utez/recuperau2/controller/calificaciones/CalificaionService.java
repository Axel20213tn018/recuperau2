package mx.edu.utez.recuperau2.controller.calificaciones;

import mx.edu.utez.recuperau2.model.calificacion.BeanCalificacion;
import mx.edu.utez.recuperau2.model.calificacion.DaoCalificaciones;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/api/calificaiones")
public class CalificaionService {

    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanCalificacion> getAll(){
        return new DaoCalificaciones().findAll();
    }
}
