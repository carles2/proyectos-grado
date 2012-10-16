package Utilidades;

/**
 * Fichero de prueba para las consulta de la base de datos
 */


import java.sql.ResultSet;
import java.sql.SQLException;


public class RellenaBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ConexionBD conexionBD=null;
        ResultSet consulta=null;
        StringBuilder respuesta=null;
        
        try {
            conexionBD = new ConexionBD("root","toor","consumoelectrico");
        } catch (ClassNotFoundException ex){
            System.out.println("Error al conectar con la base de datos");
            System.exit(1);
        }catch( SQLException ex){
            System.out.println("Error al conectar con la base de datos");
            System.exit(1);
        }
             
        try {    
            consulta=conexionBD.consulta("Select * from tnombre");
        } catch (SQLException ex) {
            System.out.println("error en la consulta SQL");
        } 
        
        System.out.println(consulta.toString());
        try {
            respuesta=conexionBD.consultaYmostrar("Select * from tnombre");
        } catch (SQLException ex) {
            System.out.println("error en la consulta SQL");
        }
        
        System.out.println(respuesta.toString());
        conexionBD.cerrarBaseDatos();
        
   }
}
