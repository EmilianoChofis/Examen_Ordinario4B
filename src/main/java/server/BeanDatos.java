package server;

public class BeanDatos {
    int id;
    String nombre;
    String apellido1;
    String apellido2;
    String curp;
    String fec_nac;
    String rfc;

    @Override
    public String toString() {
        return "BeanDatos{" +
                "nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", curp='" + curp + '\'' +
                ", fec_nac='" + fec_nac + '\'' +
                ", rfc='" + rfc + '\'' +
                '}'+"\n";
    }

    public BeanDatos() {
    }

    public BeanDatos(int id, String nombre, String apellido1, String apellido2, String curp, String fec_nac, String rfc) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.curp = curp;
        this.fec_nac = fec_nac;
        this.rfc = rfc;
    }

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

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getFec_nac() {
        return fec_nac;
    }

    public void setFec_nac(String fec_nac) {
        this.fec_nac = fec_nac;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
}
