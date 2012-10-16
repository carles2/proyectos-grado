package Utilidades;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


public class ConexionBD {
    private String userName;
    private String password;
    private String baseDatos;
    private String servidor;
    private Connection conexion=null;
    private Statement instruccion=null;
    ResultSet conjuntoResultados=null; // resultado de la ultima consulta

    /**
     * Constuctor parametrizado para conectarse a una base de dato en el servidor
     * local es decir LOCALHOST, se le pasa por parametro el usuario, la contraseña
     * y el nombre de la base de datos, puede lanzar excepciones
     * 
     * @param Usuario, nombre del usuario con acceso a la base de datos
     * @param Password, contraseña del usuario con acceso a la base de datos
     * @param BaseDatos, nombre de la base de datos,
     * @throws ClassNotFoundException, lanza esta excepcion si no esta el conector jdbc
     * @throws SQLException, lanza esta excepcion si no conecta con la base de datos
     */
    public ConexionBD(String Usuario, String Password, String BaseDatos) throws ClassNotFoundException, SQLException {
        userName=Usuario;
        password=Password;
        baseDatos=BaseDatos;
        servidor="localhost";
        inicializa();
   }

    /**
     * Constuctor parametrizado para conectarse a una base de dato en el servidor
     * local es decir LOCALHOST, se le pasa por parametro el usuario, la contraseña
     * la url donde esta el servidor y el nombre de la base de datos, puede lanzar excepciones
     * 
     * @param Usuario, nombre del usuario con acceso a la base de datos
     * @param Password, contraseña del usuario con acceso a la base de datos
     * @param BaseDatos, nombre de la base de datos,
     * @param url, direcion url de donde esta la base de datos
     * @throws ClassNotFoundException, lanza esta excepcion si no esta el conector jdbc
     * @throws SQLException, lanza esta excepcion si no conecta con la base de datos
     */
   public ConexionBD(String Usuario, String Password, String BaseDatos, String url) throws ClassNotFoundException, SQLException{
        userName=Usuario;
        password=Password;
        baseDatos=BaseDatos;
        servidor=url;
        inicializa();
   }
   
   /**
    * Crea la conexion a la base de datos con los parametros adecuados internos del obejeto.
    * 
     * @throws ClassNotFoundException, lanza esta excepcion si no esta el conector jdbc
     * @throws SQLException, lanza esta excepcion si no conecta con la base de datos
    */
   private void inicializa() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver"); 
        conexion = DriverManager.getConnection("jdbc:mysql://"+servidor+"/"+baseDatos, userName , password);
        instruccion = conexion.createStatement();
   }
   
   /**
    * Devuelve una consulta formateada  o una excepcion si la consulta es invalida
    * @param Cadena, cadena ha de contener el selec de la consulta SQL
    * 
    * @return la consulta formateada
    * @throws SQLException, se produce la excepcion si la consulta no es correcta
    */
   public StringBuilder consultaYmostrar(String Cadena) throws SQLException{
       StringBuilder resultados = new StringBuilder();
       try{
          conjuntoResultados = instruccion.executeQuery (Cadena);

          ResultSetMetaData metaDatos = conjuntoResultados.getMetaData();
          int numeroDeColumnas = metaDatos.getColumnCount();
          for (int i = 1; i <= numeroDeColumnas; i++) {
             resultados.append(metaDatos.getColumnName(i) + "\t");
          }
          resultados.append("\n");
          while (conjuntoResultados.next()) {
            for (int i = 1; i <= numeroDeColumnas; i++) {
                resultados.append(conjuntoResultados.getObject(i) + "\t");
            }
            resultados.append("\n");
          }
       } catch (SQLException ex) {
          throw ex;
       }
       return resultados;
   }
   
   public ResultSet consulta (String Cadena) throws SQLException{
        try {
            conjuntoResultados = instruccion.executeQuery (Cadena);
        } catch (SQLException ex) {
            throw ex;
        }
        return conjuntoResultados;
   }
   
   public ResultSet ultimaConsulta(){
       return conjuntoResultados;
   }
   
   public void cerrarBaseDatos(){
        try {
            conexion.close();
        } catch (SQLException ex) {
           
        }
   }

}
