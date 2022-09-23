package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {

    public static Connection getConnection(){
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/examen4b","root", "root");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Connection conexion = Conn.getConnection();
        if (conexion != null) {
            System.out.println("conexion establecida");
        }else {
            System.out.println("Error");
        }
    }
}
