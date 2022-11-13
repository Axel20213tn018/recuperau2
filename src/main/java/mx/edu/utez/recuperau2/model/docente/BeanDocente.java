package mx.edu.utez.recuperau2.model.docente;

import java.sql.Date;

public class BeanDocente {
    private long numEmpleado;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String curp;

    public BeanDocente() {

    }

    public long getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(long numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
}
