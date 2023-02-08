package db;
import java.sql.*;

public class database {
    Connection cn=null;

    public Connection iniciar(){
        //si no hay una conexion creada inicia una
        if(!comprobar()){
            try {
                //Class.forName( "com.mysql.cj.jdbc.Driver" ); //innecesario en las ultimas versiones del jdbc

                //editen el string con los valores de su db     localhost:{puerto}/{nombredeladb},{user},{password}
                cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
                System.out.println("cn iniciada");
            } catch (SQLException e) {
                System.out.print(e+"\n"+e.getMessage());
            }
        }
        //devuelve la conexion
        return cn;
    }
    public boolean comprobar(){
        if(cn==null){
            System.out.println("cn nula");
            return false;
        }else{
            System.out.println("cn existente");
            return true;
        }
    }

    public void cerrar(){
        try {
            cn.close();
            System.out.println("cn cerrada");
        } catch (SQLException e) {
            System.out.print(e+"\n"+e.getMessage());
        }
    }
}