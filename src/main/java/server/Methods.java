package server;

import java.util.List;
import server.BeanDatos;
public class Methods {


    public boolean save(String nombre, String apellido1, String apellido2, String curp, String fec_nac, String rfc) {
        Dao dao = new Dao();
        return dao.saveDatos(nombre, apellido1, apellido2, curp, fec_nac, rfc);
    }
    public String consultaTotal(){
        Dao dao = new Dao();
        List<BeanDatos> list = dao.ConsultaTotal();
        String cadena = "";
        for (BeanDatos datos:list){
            cadena=" "+cadena+datos.toString();
        }
        return cadena;
    }

    public String consulta(String curp){
        Dao dao = new Dao();
        BeanDatos dato = dao.consulta(curp);
        return dato.toString();
    }

    public boolean delete(String curp){
        Dao dao = new Dao();

        return dao.delete(curp);
    }

}
