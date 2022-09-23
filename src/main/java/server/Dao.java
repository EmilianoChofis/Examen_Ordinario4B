package server;

import utils.Conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    public boolean saveDatos(String nombre, String apellido1, String apellido2, String curp
    , String fec_nac, String rfc){
        boolean result = false;
        try(
                Connection con = Conn.getConnection();
                PreparedStatement pstm = con.prepareStatement("insert into datos (nombre, apellido1, apellido2, curp, " +
                        "fec_nac, rfc) values(?,?,?,?,?,?);")
        ){
            pstm.setString(1, nombre);
            pstm.setString(2, apellido1);
            pstm.setString(3, apellido2);
            pstm.setString(4,curp);
            pstm.setString(5, fec_nac);
            pstm.setString(6, rfc);



            result = pstm.executeUpdate()==1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public BeanDatos consulta(String curp){
        BeanDatos dato = new BeanDatos();
        try(
                Connection con = Conn.getConnection();
                PreparedStatement pstm = con.prepareStatement("select * from datos where curp=?;");
        ){
            pstm.setString(1,curp);
            ResultSet rs= pstm.executeQuery();
            while (rs.next()){
                dato.setNombre(rs.getString("nombre"));
                dato.setApellido1(rs.getString("apellido1"));
                dato.setApellido2(rs.getString("apellido2"));
                dato.setCurp(rs.getString("curp"));
                dato.setFec_nac(rs.getString("fec_nac"));
                dato.setRfc(rs.getString("rfc"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return dato;
    }


    public List<BeanDatos> ConsultaTotal(){
        List<BeanDatos> consultaTotal = new ArrayList<>();
        try {
            Connection connection = Conn.getConnection();
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from datos;");

            while (rs.next()){ //pregunta si hay otro y lo agrega hasta no encontrar ningun registro
                BeanDatos datos = new BeanDatos();
                datos.setNombre(rs.getString("nombre"));
                datos.setApellido1(rs.getString("apellido1"));
                datos.setApellido2(rs.getString("apellido2"));
                datos.setCurp(rs.getString("curp"));
                datos.setFec_nac(rs.getString("fec_nac"));
                datos.setRfc(rs.getString("rfc"));
                consultaTotal.add(datos);
            }
            rs.close();
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return consultaTotal;
    }


    public boolean delete(String curp) {
        boolean result = false;
        try(
                Connection connection =Conn.getConnection();
                PreparedStatement pstm = connection.prepareStatement("delete from datos where curp =?;");
        ){
            pstm.setString(1,curp);
            result = pstm.executeUpdate()==1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
