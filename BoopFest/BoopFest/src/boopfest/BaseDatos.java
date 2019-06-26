package boopfest;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class BaseDatos {
    public BaseDatos() {
        
    }
    
    String jdbcUrl = "jdbc:oracle:thin:@//oracle0.ugr.es:1521/practbd.oracle0.ugr.es";
    String usuario = "x5520228";
    String password = "x5520228"; 
    Connection conexion;
    
    public void obtenerConexion() throws SQLException{
        try{
            Class.forName("oracle.jdbc.OracleDriver"); 
            conexion = DriverManager.getConnection(jdbcUrl, usuario, password);
        }catch(Exception e){
            System.out.println("Error al conectar con la base de datos");
        }
    }
    
    public ArrayList<String> obtenerNombresGrupos() throws SQLException{
        ArrayList<String> nombreGrupos = new ArrayList<String>();
        
        this.obtenerConexion();
        Statement miStatement = conexion.createStatement();
        
        ResultSet miResultSet = miStatement.executeQuery("SELECT * FROM GRUPO");
        while(miResultSet.next()){
            nombreGrupos.add(miResultSet.getString("NOMBRE_GRUPO"));
        }
        
        return nombreGrupos;
    }
    
    public ArrayList<String> obtenerNombresFestivales() throws SQLException{
        ArrayList<String> nombreFestivales = new ArrayList<String>();
        
        this.obtenerConexion();
        Statement miStatement = conexion.createStatement();
        
        ResultSet miResultSet = miStatement.executeQuery("SELECT * FROM FESTIVAL");
        while(miResultSet.next()){
            nombreFestivales.add(miResultSet.getString("ID_FESTIVAL"));
        }
        
        return nombreFestivales;
    }
    
    public ArrayList<String> obtenerNombresEscenarios() throws SQLException{
        ArrayList<String> nombreEscenarios = new ArrayList<String>();
        
        this.obtenerConexion();
        Statement miStatement = conexion.createStatement();
        
        ResultSet miResultSet = miStatement.executeQuery("SELECT * FROM ESCENARIO");
        while(miResultSet.next()){
            nombreEscenarios.add(miResultSet.getString("ID_ESCENARIO"));
        }
        
        return nombreEscenarios;
    }
    
    String convertirAFecha(String fecha){
        
        StringTokenizer st1 = new StringTokenizer(fecha,"-");
        String anio = st1.nextToken();
        String mes = st1.nextToken();
        String dia = st1.nextToken();
        dia = dia.substring(0,2);
        
        String fechaValida = dia + "/" + mes + "/" + anio;
        
        return fechaValida;
    }
    
    public ArrayList<String> obtenerIntervaloFechas(String fIni, String fFin) {
        ArrayList<String> fechasFestival = new ArrayList<String>();
        int diaInicio = Integer.parseInt(fIni.substring(0,2));
        int diaFin = Integer.parseInt(fFin.substring(0,2));
        int mes = Integer.parseInt(fIni.substring(3,5));
        int anio = Integer.parseInt(fIni.substring(6));
        
        for(int i=diaInicio ; i<=diaFin ; i++){
            String str = "";
            if (i<10)
                str="0";
            
            str+=Integer.toString(i);
            
            if(mes<10)
                str+="/0" + mes + "/" + anio;
            else
                str+="/" + mes + "/" + anio;
            
            if(i==31){
                i=1;
                
                if(mes!=12)
                    mes++;
                else{
                    mes=1;
                    anio++;
                }
            }
            fechasFestival.add(str);
        }
        return fechasFestival;
    }
    
    public ArrayList<String> obtenerFechasFestival(String festival) throws SQLException{
        ArrayList<String> fechasFestival = new ArrayList<String>();
        
        this.obtenerConexion();
        Statement miStatement = conexion.createStatement();
        
        //cambiado NOMBRE_FESTIVAL por ID_FESTIVAL
        ResultSet miResultSet = miStatement.executeQuery("SELECT * FROM FESTIVAL WHERE ID_FESTIVAL='"+festival+"'");
        
        miResultSet.next();
        String fechaInicio = miResultSet.getString("FECHA_INICIO");
        String fechaFin = miResultSet.getString("FECHA_FIN");
        
        String fIni = convertirAFecha(fechaInicio);
        String fFin = convertirAFecha(fechaFin);
        
        fechasFestival = obtenerIntervaloFechas(fIni, fFin);
                       
        return fechasFestival;
    }
    
    public ArrayList<String> obtenerLineas() throws SQLException{
        ArrayList<String> lineas = new ArrayList<String>();
        
        this.obtenerConexion();
        Statement miStatement = conexion.createStatement();
        
        ResultSet miResultSet = miStatement.executeQuery("SELECT * FROM LINEA_CONTIENE");
        while(miResultSet.next()){
            lineas.add(miResultSet.getString("NOMBRE_LINEA"));
        }
        
        return lineas;
    }
    
    public void asignarGrupoAEscenario(String idFest, String nombreGrupo, String idEsc, String fecha, String hora, String duracion) throws SQLException {
        this.obtenerConexion();
        Statement stmt = conexion.createStatement();
        String sentencia =  "INSERT INTO ACTUA_ORGANIZA VALUES('" + nombreGrupo + "','" + idEsc 
                            + "', to_timestamp('" + fecha + " " + hora
                            + "','DD-MM-YYYY HH24:MI')," + duracion + ",'" + idFest + "')";
        
                System.out.println(sentencia);
        
        stmt.executeUpdate(sentencia);
        //conexion.closte();
    }
    
    public void asignarVehiculoLinea(String matricula, String vehiculo, String capacidad, String marca, String modelo,
                                     String estado, String disponibilidad, String nombreLinea) throws SQLException{
        this.obtenerConexion();
        Statement stmt = conexion.createStatement();
        String sentencia =  "INSERT INTO VEHICULO_REALIZA VALUES('" + matricula + "','" + vehiculo 
                            + "','" + capacidad + "','" + marca + "','" + modelo + "','" + estado 
                            + "','" + disponibilidad + "','" + nombreLinea + "')";
        
                System.out.println(sentencia);
        
        stmt.executeUpdate(sentencia);
    }
    
    public void asignarPersonalFestival(String codEmpleado, String idFestival, String hEntrada, String hSalida) throws SQLException {
        this.obtenerConexion();
        Statement stmt = conexion.createStatement();
        String sentencia = "INSERT INTO ASIGNA VALUES('" + idFestival + "','" + codEmpleado 
                           + "', to_timestamp('" + hEntrada + "','HH24:MI'), to_timestamp('" 
                           + hSalida + "','HH24:MI'))";
        
                System.out.println(sentencia);
        
        stmt.executeUpdate(sentencia);
    }
    
    public void comprarEntrada(String idFestival, String nombre, String dni, String tipoEntrada, String precio) throws SQLException {
        this.obtenerConexion();
               
        Statement stmt = conexion.createStatement();
        String sentencia = "INSERT INTO ENTRADA VALUES('" + idFestival + "','" + dni 
                           + "','" + tipoEntrada + "'," + precio + ")";
        
                System.out.println(sentencia);
        
        stmt.executeUpdate(sentencia);
    }
    
    public static void main(String[] args){
        
    }

}
