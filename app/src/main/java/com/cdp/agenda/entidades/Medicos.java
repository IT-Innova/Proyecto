package com.cdp.agenda.entidades;

public class Medicos {

    private int id;
    private String nombre;
    private String cip;
    private String dni;
    private String fechaNacimiento;
    private String anhosExperiencia;
    private String telefono;
    private String correoElectronico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCip(){
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getAnhosExperiencia(){
        return anhosExperiencia;
    }

    public void setAnhosExperiencia(String anhosExperiencia){
        this.anhosExperiencia = anhosExperiencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico(){return correoElectronico;}

    public void setCorreoElectronico(String correoElectronico){
        this.correoElectronico = correoElectronico;
    }
}
