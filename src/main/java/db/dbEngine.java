package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbEngine {
    public Connection conexion;
    private database db;
    private String[] array=null;

    //constructor
    public dbEngine(){
        //instancia la clase database
        db = new database();
        //inicia la conexion a la db
        conexion= db.iniciar();
    }

    //procedimiento para sentencias sql que no tienen return
    public void execute(String consulta){

        try {
            conexion= db.iniciar();
            PreparedStatement ps = conexion.prepareStatement(consulta);
            for (int i = 0; i < array.length; i++) {
                ps.setObject(i + 1, array[i]);
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //funcion para consultas sql con return
    public ResultSet rsreturn(String codigoDB) {
        ResultSet rs = null;
        try {
            conexion= db.iniciar();
            PreparedStatement ps = conexion.prepareStatement(codigoDB);
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}