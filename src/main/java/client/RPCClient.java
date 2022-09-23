package client;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import server.BeanDatos;
import server.Methods;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;


public class RPCClient {
    static Scanner sc =  new Scanner(System.in);
    public static void main(String[] args) throws MalformedURLException, XmlRpcException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:1500"));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);

        SecureRandom random = new SecureRandom();
        Methods methods = new Methods();
        StringBuilder rfc_builder = new StringBuilder();
        Methods authentication = new Methods();
        BeanDatos authenticationDatos = new BeanDatos();
        String nombre = "", apellido1="", apellido2="", curp="", anio="", mes="", dia="", fec_nac="", rfc="", response="";
        boolean result;
        int option;
        do {
        System.out.println("¿Qué accion desea realizar?\n" +
                "1. Registar\n" +
                "2. Modificar \n" +
                "3. Consulta\n" +
                "4. Consulta todos\n" +
                "5. Eliminar\n" +
                "6. Salir");


        option = sc.nextInt();

            switch (option) {
                case 1:


                    System.out.println("dime tu nombre(s)");
                    nombre = sc.next();
                    System.out.println("dime tu primer apellido");
                    apellido1 = sc.next();
                    System.out.println("Dime tu segundo apellido");
                     apellido2 = sc.next();
                    System.out.println("dime tu curp");
                    curp = sc.next();
                    System.out.println("Escribe tu año de nacimineto");
                     anio = sc.next();
                    System.out.println("Escribe tu mes de nacimiento");
                     mes = sc.next();
                    System.out.println("Escribe tu dia de nacimiento con dos digitos");
                    dia = sc.next();
                    fec_nac = anio+"/"+mes+"/"+dia;
                    authenticationDatos = authentication.existe(curp);
                    System.out.println(authenticationDatos.getId());
                    if (authenticationDatos.getId()==0){
                        for (int i = 0; i <= 6; i++) {
                            if (i <= 1) {
                                rfc_builder.append(apellido1.charAt(i));
                            } else {
                                if (i == 3) {
                                    rfc_builder.append(apellido2.charAt(0));
                                } else {
                                    if (i == 4) {
                                        rfc_builder.append(nombre.charAt(0));
                                    } else {
                                        if (i == 5) {
                                            rfc_builder.append(anio.charAt(2));
                                            rfc_builder.append(anio.charAt(3));
                                            rfc_builder.append(mes);
                                            rfc_builder.append(dia);
                                        }
                                        if (i == 6) {
                                            final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                                            for (int j = 0; j < 3; j++) {
                                                int randomIndex = random.nextInt(chars.length());
                                                rfc_builder.append(chars.charAt(randomIndex));
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        rfc = String.valueOf(rfc_builder);

                        Object[] datos = {nombre, apellido1, apellido2, curp, fec_nac, rfc};
//                  response = (String) client.execute("Methods.save",datos);
                        result= methods.save(nombre, apellido1, apellido2, curp, fec_nac, rfc.toUpperCase());
                        if (result){
                            System.out.println("Persona registrada existosamente su RFC es: " + rfc.toUpperCase());
                        }else {
                            System.out.println("no se pudo");
                        }

                    }else {
                        System.out.println("Ya esta registrado");
                    }

//
                    break;
                case 2:
                    System.out.println("Dime el curp de la persona a modificar");
                    String curp_mod = sc.next();
                    Object [] modify = {curp_mod};
                    authenticationDatos = authentication.existe(curp_mod);
//                    System.out.println(authenticationDatos.getId());
                    if (authenticationDatos.getId()!=0){

                        System.out.println("dime tu nombre(s)");
                        nombre = sc.next();
                        System.out.println("dime tu primer apellido");
                        apellido1 = sc.next();
                        System.out.println("Dime tu segundo apellido");
                        apellido2 = sc.next();
                        System.out.println("dime tu curp");
                        curp = sc.next();
                        System.out.println("Escribe tu año de nacimineto");
                        anio = sc.next();
                        System.out.println("Escribe tu mes de nacimiento");
                        mes = sc.next();
                        System.out.println("Escribe tu dia de nacimiento con dos digitos");
                        dia = sc.next();
                        fec_nac = anio+"/"+mes+"/"+dia;

                        for (int i = 0; i <= 6; i++) {
                            if (i <= 1) {
                                rfc_builder.append(apellido1.charAt(i));
                            } else {
                                if (i == 3) {
                                    rfc_builder.append(apellido2.charAt(0));
                                } else {
                                    if (i == 4) {
                                        rfc_builder.append(nombre.charAt(0));
                                    } else {
                                        if (i == 5) {
                                            rfc_builder.append(anio.charAt(2));
                                            rfc_builder.append(anio.charAt(3));
                                            rfc_builder.append(mes);
                                            rfc_builder.append(dia);
                                        }
                                        if (i == 6) {
                                            final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                                            for (int j = 0; j < 3; j++) {
                                                int randomIndex = random.nextInt(chars.length());
                                                rfc_builder.append(chars.charAt(randomIndex));
                                            }
                                        }
                                    }
                                }
                            }
                        }
//
                        rfc = String.valueOf(rfc_builder);

                        Object[] datos_mod = {nombre, apellido1, apellido2, curp, fec_nac, rfc};
//                  response = (String) client.execute("Methods.save",datos);
                        int id = authenticationDatos.getId();
                        result= methods.update(nombre, apellido1, apellido2, curp, fec_nac, rfc.toUpperCase(), id);
                        if (result){
                            System.out.println("Persona actualizada existosamente su nuevo RFC es: " + rfc.toUpperCase());
                        }else {
                            System.out.println("no se pudo");
                        }
                    }else {
                        System.out.println("No esta registrado ese curp");
                    }
                    break;
                case 3:
                    System.out.println("Curp de la persona a buscar");
                    curp = sc.next();
                    Object [] dato = {curp};
                    /*response = (String) client.execute("Methods.consulta",dato);
                    System.out.println(response);*/
                    System.out.println(methods.consulta(curp));
                    break;
                case 4:
                    System.out.println("Historial\n");
                    Object [] list = {};
                    System.out.println(methods.consultaTotal()+"\n");
                       /*  response = (String) client.execute("Methods.delete",delete);
                    System.out.println(response);*/
                    break;
                case 5:
                    System.out.println("Curp de la persona a eliminar");
                    curp = sc.next();
                    System.out.println(curp);
                    Object [] delete = {curp};
                    result = methods.delete(curp);
//                    System.out.println(curp);
                    if (result){
                        System.out.println("Exitoso");
                    }else {
                        System.out.println("mal");
                    }

                  /*  response = (String) client.execute("Methods.delete",delete);
                    System.out.println(response);*/
                    break;

            }
        }while (option!=6);




    }
}

/*do {
                    System.out.println("¿Qué campo desea modificar?\n" +
                            "1. Nombre\n" +
                            "2. Primer apellido\n" +
                            "3. Segundo apellido\n" +
                            "4. CURP\n" +
                            "5. Año\n" +
                            "6. Mes\n" +
                            "7. Dia\n" +
                            "Teclee 8 para cerrar este menú");
                    int option_mod = sc.nextInt();

                        switch (option_mod){
                            case 1:
                                System.out.println("Nombre(s)");
                                nombre = sc.nextLine();
                                break;
                            case 2:
                                System.out.println("Primer apellido");
                                apellido1 = sc.nextLine();
                                break;
                            case 3:
                                System.out.println("Segundo apellido");
                                apellido2 = sc.nextLine();
                                break;
                            case 4:
                                System.out.println("CURP");
                                curp = sc.nextLine();
                                break;
                            case 5:
                                System.out.println("Año");
                                anio = sc.nextLine();
                            case 6:
                                System.out.println("Mes");
                                mes = sc.nextLine();
                                break;
                            case 7:
                                System.out.println("Dia");
                                dia = sc.nextLine();
                                break;
                        }
                    }while (option_mod!=8);*/
