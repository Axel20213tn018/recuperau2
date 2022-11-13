package mx.edu.utez.recuperau2.model.alumno;

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

public class DaoAlumno implements Repository<BeanAlumno> {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    MySQL mySQL = new MySQL();
    @Override
    public List<BeanAlumno> findAll() {
        List<BeanAlumno> alumnos = new ArrayList<>();
        BeanAlumno alumno;
        try{
            conn = mySQL.getConnection();
            String query = "SELECT * FROM alumno";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                alumno = new BeanAlumno();
                alumno.setMatricula(rs.getString("matricula"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellidos(rs.getString("apellidos"));
                alumno.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                alumno.setCurp(rs.getString("curp"));
                alumnos.add(alumno);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoAlumno.class.getName()).log(Level.SEVERE, "Error --> Lista Alumnos" + e.getMessage());
        }finally {
            mySQL.close(conn,ps,rs);
        }
        return alumnos;
    }

    @Override
    public Response<BeanAlumno> findById(Long id) {
        BeanAlumno alumno = null;
        Response<BeanAlumno> response = new Response<>();
        try {
            conn = mySQL.getConnection();
            String query = "SELECT * FROM alumno where matricula=? ";

            ps= conn.prepareStatement(query);
            ps.setLong(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                alumno = new BeanAlumno();
                alumno.setMatricula(rs.getString("matricula"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellidos(rs.getString("apellidos"));
                alumno.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                alumno.setCurp(rs.getString("curp"));

                if (ps.execute()){
                    response.setStatus(200);
                    response.setMessage("Alumno Localizado correctamente");
                    response.setError(false);
                    response.setData(alumno);
                }else{
                    response.setStatus(400);
                    response.setMessage("Alumno no encontrado");
                    response.setError(true);
                    response.setData(null);
                }
            }
        }catch (SQLException e){

            Logger.getLogger(DaoAlumno.class.getName()).log(Level.SEVERE,"Error -> FindById "+e.getMessage());
        }finally {
            mySQL.close(conn,ps,rs);
        }

        return response;
    }

    @Override
    public Response<BeanAlumno> save(BeanAlumno alumno) {
        Response<BeanAlumno> response = new Response<>();
        try{
            conn = mySQL.getConnection();
            String query = "INSERT INTO alumno VALUES ?,?,?,?,?";
            ps = conn.prepareStatement(query);
            ps.setString(1, alumno.getMatricula());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getApellidos());
            ps.setDate(4, alumno.getFechaNacimiento());
            ps.setString(5, alumno.getCurp());
            if (ps.execute()){
                response.setStatus(200);
                response.setMessage("alumno Agregado Correctamente");
                response.setError(false);
                response.setData(alumno);
            }else{
                response.setStatus(400);
                response.setMessage("Error al Agregar alumno");
                response.setError(true);
                response.setData(null);
            }
            return response;
        }catch (SQLException e){
            Logger.getLogger(DaoAlumno.class.getName()).log(Level.SEVERE,"Error -> FindById "+e.getMessage());
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
    public Response<BeanAlumno> update(BeanAlumno alumno) {
        Response<BeanAlumno> response = new Response<>();
        try{
            conn = mySQL.getConnection();
            String query = "UPDATE alumno SET (?,?,?,?,?) WHERE matricula = ?";
            ps = conn.prepareStatement(query);
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getApellidos());
            ps.setDate(4, alumno.getFechaNacimiento());
            ps.setString(5, alumno.getCurp());
            ps.setString(1, alumno.getMatricula());
            if (ps.executeUpdate()==1){
                response.setStatus(200);
                response.setMessage("alumno Actualizado Correctamente");
                response.setError(false);
                response.setData(alumno);
            }else{
                response.setStatus(400);
                response.setMessage("Error al Actualizar alumno");
                response.setError(true);
                response.setData(null);
            }
            return response;
        }catch (SQLException e){
            Logger.getLogger(DaoAlumno.class.getName()).log(Level.SEVERE,"Error -> FindById "+e.getMessage());
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
    public Response<BeanAlumno> delete(Long id) {
        return null;
    }
}
