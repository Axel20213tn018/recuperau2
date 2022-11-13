package mx.edu.utez.recuperau2.controller.docente;

import mx.edu.utez.recuperau2.model.docente.BeanDocente;
import mx.edu.utez.recuperau2.model.docente.DaoDocente;
import mx.edu.utez.recuperau2.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/docente")
public class DocenteService {
    @GET
    @Path("/")
    @Produces (value = {"application/json"})
    public List<BeanDocente> getAll(){
        return new DaoDocente().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response<BeanDocente> find(@PathParam("id") Long id){
        return new DaoDocente().findById(id);
    }

    @POST//Insercion
    @Path("/save") //"api/docente"
    @Consumes(MediaType.APPLICATION_JSON)//Consume Json
    @Produces(MediaType.APPLICATION_JSON)// Retorna Json
    public Response<BeanDocente> save(BeanDocente docente){
        return  new DaoDocente().save(docente);
    }

    @POST//Insercion
    @Path("/update") //"api/docente"
    @Consumes(MediaType.APPLICATION_JSON)//Consume Json
    @Produces(MediaType.APPLICATION_JSON)// Retorna Json
    public Response<BeanDocente> update(BeanDocente docente){
        return  new DaoDocente().update(docente);
    }


    @POST
    @Path("/delete/{id}") //"api/docente"
    @Produces(value = {"application/json"})
    public Response<BeanDocente> delete(@PathParam("id")Long id ){
        return new DaoDocente().delete(id);
    }
}
