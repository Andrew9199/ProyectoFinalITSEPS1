import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class InsertExample {
    
    public static void main(String[] args) throws SQLException {
        // Configurar la conexión a la base de datos
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datesum", "root", "");
        
        // Crear una nueva marca de tiempo para la fecha actual
        Date fechaActual = new Date();
        Timestamp marcaDeTiempo = new Timestamp(fechaActual.getTime());
        
        // Insertar los datos en la base de datos utilizando una sentencia preparada
        PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO mi_tabla (nombre, fecha) VALUES (?, ?)");
        sentencia.setString(1, "Juan");
        sentencia.setTimestamp(2, marcaDeTiempo);
        int filasInsertadas = sentencia.executeUpdate();
        
        System.out.println(filasInsertadas + " fila(s) insertada(s) correctamente");
        
        // Cerrar la conexión a la base de datos
        conexion.close();
    }
}
