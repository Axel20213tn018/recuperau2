package mx.edu.utez.recuperau2.model.calificacion;

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

public class DaoCalificaciones implements Repository<BeanCalificacion> {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    MySQL mySQL = new MySQL();

    @Override
    public List<BeanCalificacion> findAll() { // Mostrar Todas las calificaciones + El Promedio
        List<BeanCalificacion> calificaciones = new ArrayList<>();
        BeanCalificacion calificacion;
        try{
            conn = mySQL.getConnection();
            String query = "SELECT * FROM docente";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                calificacion = new BeanCalificacion();
                calificacion.setMateria(rs.getString("materia"));
                calificacion.setCalificacion(rs.getDouble("calificacion"));
                calificacion.setIdDocente(rs.getLong("id_docente"));
                calificacion.setIdAlumno(rs.getString("id_alumn"));
                calificaciones.add(calificacion);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoCalificaciones.class.getName()).log(Level.SEVERE, "Error --> Lista Calificaciones" + e.getMessage());
        }finally {
            mySQL.close(conn,ps,rs);
        }
        return calificaciones;
    }

    @Override
    public Response<BeanCalificacion> findById(Long id) {
        return null;
    }

    @Override
    public Response<BeanCalificacion> save(BeanCalificacion object) {
        return null;
    }

    @Override
    public Response<BeanCalificacion> update(BeanCalificacion object) {
        return null;
    }

    @Override
    public Response<BeanCalificacion> delete(Long id) {
        return null;
    }
}
