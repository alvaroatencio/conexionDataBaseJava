package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static Resources.entitys.*;

public class Main {
    public static void main(String[] args) {
        //creamos un usuario
        crearUsuario();
        //mostramos datos por consola
        mostrarUsuarios();
    }
    public static void crearUsuario(){
        // CREAR USUARIO

        //SIEMPRE ES MEJOR EL TRY CATCH PARA CUALQUIER COSA CON SQL ANTES QUE EL THROWS EN LA FUNCION
        try {
            //insertUsuarios esta declarado en la interface entitys.java
            PreparedStatement pstmt = dbE.conexion.prepareStatement(insertUsuarios);
            //REEMPLAZAR LOS SETS CON LOS VALORES A GUARDAR
            pstmt.setString(1, "asdddd");
            pstmt.setString(2, "asdadaasd@gmail.com");
            pstmt.setString(3, "qqqq");

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex+"\n"+ex.getMessage());
        }
    }

    public static void mostrarUsuarios(){
        //MOSTRAR USUARIOS
        try {
            System.out.println();

            //Resulset es una clase de sql que guarda objetos, tablas. En este caso guardo los datos obtenidos del select
            //select esta declarado en interface entitys.java
            ResultSet rs= dbE.rsreturn(select+"usuarios");
            //con metadata obtenemos los valores del schema (nombres de las columnas, tipo de dato, etc)
            ResultSetMetaData metaDatos = rs.getMetaData();
            // Se obtiene el numero de columnas.
            int numeroColumnas = metaDatos.getColumnCount();
            //creo un array para guardar los nombres de las columnas
            Object[] etiquetas = new Object[numeroColumnas];

            for (int i = 0; i < numeroColumnas; i++) {
                // Se guardan los nombres de cada columna
                etiquetas[i] = metaDatos.getColumnLabel(i+1);
                //salida a consola con el espaciado mas cutre que tenia
                System.out.print(etiquetas[i]+"\t\t\t\t");
            }
            System.out.println();

            //recorro el resulset para obtener los datos de las filas
            while (rs.next()) {
                //creo un objeto para almacenar las filas
                Object[] datosFila = new Object[numeroColumnas];

                for (int i = 0; i < numeroColumnas; i++) {
                    //pongan el +1 o explota
                    datosFila[i] = rs.getObject(i + 1);
                    //salida a consola con el espaciado mas cutre que tenia
                    System.out.print(datosFila[i]+"\t\t\t\t");
                }
                //espaciado entre filas
                System.out.println();
            }
            rs.close();
            dbE.conexion.close();

        }catch (Exception e){
            System.out.println(e +"\n"+e.getMessage());
        }
    }
}
