package mx.edu.utez.recuperau2.controller.alumno;

import mx.edu.utez.recuperau2.model.alumno.BeanAlumno;
import mx.edu.utez.recuperau2.model.alumno.DaoAlumno;
import mx.edu.utez.recuperau2.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/alumno")
public class AlumnoService {

    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanAlumno> getAll(){
        return new DaoAlumno().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response<BeanAlumno> find(@PathParam("id") Long id){
        return new DaoAlumno().findById(id);
    }

    @POST//Insercion
    @Path("/save") //"api/alumno"
    @Consumes(MediaType.APPLICATION_JSON)//Consume Json
    @Produces(MediaType.APPLICATION_JSON)// Retorna Json
    public Response<BeanAlumno> save(BeanAlumno docente){
        return  new DaoAlumno().save(docente);
    }

    @POST//Insercion
    @Path("/update") //"api/alumno"
    @Consumes(MediaType.APPLICATION_JSON)//Consume Json
    @Produces(MediaType.APPLICATION_JSON)// Retorna Json
    public Response<BeanAlumno> update(BeanAlumno docente){
        return  new DaoAlumno().update(docente);
    }


    @POST
    @Path("/delete/{id}") //"api/alumno"
    @Produces(value = {"application/json"})
    public Response<BeanAlumno> delete(@PathParam("id")Long id ){
        return new DaoAlumno().delete(id);
    }
}
