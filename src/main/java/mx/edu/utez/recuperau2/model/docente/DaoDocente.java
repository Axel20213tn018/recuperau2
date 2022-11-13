package mx.edu.utez.recuperau2.model.docente;

import mx.edu.utez.recuperau2.model.Repository;
import mx.edu.utez.recuperau2.utils.MySQL;
import mx.edu.utez.recuperau2.utils.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoDocente implements Repository<BeanDocente> {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    MySQL mySQL = new MySQL();

    @Override
    public List<BeanDocente> findAll() {
        List<BeanDocente> docentes = new ArrayList<>();
        BeanDocente docente;
        try{
            conn = mySQL.getConnection();
            String query = "SELECT * FROM docente";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                docente = new BeanDocente();
                docente.setNumEmpleado(rs.getLong("numero_de_empleado"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellidos(rs.getString("apellidos"));
                docente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                docente.setCurp(rs.getString("curp"));
                docentes.add(docente);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoDocente.class.getName()).log(Level.SEVERE, "Error --> Lista Docentes" + e.getMessage());
        }finally {
            mySQL.close(conn,ps,rs);
        }
        return docentes;
    }

    @Override
    public Response<BeanDocente> findById(Long id) {
        BeanDocente docente = null;
        Response<BeanDocente> response = new Response<>();
        try {
            conn = mySQL.getConnection();
            String query = "SELECT * FROM docente where numero_de_empleado=? ";

            ps= conn.prepareStatement(query);
            ps.setLong(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                docente = new BeanDocente();
                docente.setNumEmpleado(rs.getLong("numero_de_empleado"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellidos(rs.getString("apellidos"));
                docente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                docente.setCurp(rs.getString("curp"));

                if (ps.execute()){
                    response.setStatus(200);
                    response.setMessage("Docente Localizado correctamente");
                    response.setError(false);
                    response.setData(docente);
                }else{
                    response.setStatus(400);
                    response.setMessage("Docente no encontrado");
                    response.setError(true);
                    response.setData(null);
                }
            }
        }catch (SQLException e){

            Logger.getLogger(DaoDocente.class.getName()).log(Level.SEVERE,"Error -> FindById "+e.getMessage());
        }finally {
            mySQL.close(conn,ps,rs);
        }

        return response;
    }

    @Override
    public Response<BeanDocente> save(BeanDocente docente) {
        Response<BeanDocente> response = new Response<>();
        try{
            conn = mySQL.getConnection();
            String query = "INSERT INTO docente VALUES ?,?,?,?,?";
            ps = conn.prepareStatement(query);
            ps.setLong(1, docente.getNumEmpleado());
            ps.setString(2, docente.getNombre());
            ps.setString(3, docente.getApellidos());
            ps.setDate(4, docente.getFechaNacimiento());
            ps.setString(5, docente.getCurp());
            if (ps.execute()){
                response.setStatus(200);
                response.setMessage("Docente Agregado Correctamente");
                response.setError(false);
                response.setData(docente);
            }else{
                response.setStatus(400);
                response.setMessage("Error al Agregar Docente");
                response.setError(true);
                response.setData(null);
            }
            return response;
        }catch (SQLException e){
            Logger.getLogger(DaoDocente.class.getName()).log(Level.SEVERE,"Error -> FindById "+e.getMessage());
            response.setStatus(400);
            response.setMessage("Error"+e);
            response.setError(false);
            response.setData(null);
        }finally {
            mySQL.close(conn,ps,rs);
        }
        return response;
    }

    @Override
    public Response<BeanDocente> update(BeanDocente docente) {
        Response<BeanDocente> response = new Response<>();
        try{
            conn = mySQL.getConnection();
            String query = "UPDATE docente SET (?,?,?,?,?) WHERE numero_de_empleado = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, docente.getNombre());
            ps.setString(2, docente.getApellidos());
            ps.setDate(3, docente.getFechaNacimiento());
            ps.setString(4, docente.getCurp());
            ps.setLong(5, docente.getNumEmpleado());
            if (ps.execute()){
                response.setStatus(200);
                response.setMessage("Docente Agregado Correctamente");
                response.setError(false);
                response.setData(docente);
            }else{
                response.setStatus(400);
                response.setMessage("Error al Agregar Docente");
                response.setError(true);
                response.setData(null);
            }
            return response;
        }catch (SQLException e){
            Logger.getLogger(DaoDocente.class.getName()).log(Level.SEVERE,"Error -> FindById "+e.getMessage());
            response.setStatus(400);
            response.setMessage("Error"+e);
            response.setError(false);
            response.setData(null);
        }finally {
            mySQL.close(conn,ps,rs);
        }
        return response;
    }

    @Override
    public Response<BeanDocente> delete(Long id) {
        return null;
    }
}
