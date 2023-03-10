import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EnSaRegistrator extends JFrame {
    private JTextField idProfessorField;
    private JTextField entradaProfesorField;
    private JTextField salidaProfesorField;
    private JLabel registroStatusLabel;

    public EnSaRegistrator() {
        // Configuración de la ventana
        setTitle("ITSEPRegistrator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Creación de los componentes de la interfaz
        JLabel idProfessorLabel = new JLabel("Id del profesor:");
        idProfessorField = new JTextField(10);
        JLabel entradaProfesorLabel = new JLabel("Hora de salida del profesor:");
        entradaProfesorField = new JTextField(10);
        // Agregar el place holder
        entradaProfesorField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if (entradaProfesorField.getText().isEmpty()) {
                    entradaProfesorField.setText("Hora AÑO/MES/DIA");
                    entradaProfesorField.setForeground(Color.GRAY);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (entradaProfesorField.getText().equals("Hora AÑO/MES/DIA")) {
                    entradaProfesorField.setText("");
                    entradaProfesorField.setForeground(Color.BLACK);
                }
            }
        });

        JLabel salidaProfesorLabel = new JLabel("Hora de salida del profesor:");
        salidaProfesorField = new JTextField(10);
        // Agregar el place holder
        salidaProfesorField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if (salidaProfesorField.getText().isEmpty()) {
                    salidaProfesorField.setText("AÑO/MES/DIA");
                    salidaProfesorField.setForeground(Color.GRAY);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (salidaProfesorField.getText().equals("AÑO/MES/DIA")) {
                    salidaProfesorField.setText("");
                    salidaProfesorField.setForeground(Color.BLACK);
                }
            }
        });

        JButton registroStatusButton = new JButton("Registrar");
        registroStatusLabel = new JLabel();
        // Manejador del botón de sumar
        registroStatusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });

        // Configuración del diseño de la interfaz
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(idProfessorLabel);
        panel.add(idProfessorField);
        panel.add(entradaProfesorLabel);
        panel.add(entradaProfesorField);
        panel.add(salidaProfesorLabel);
        panel.add(salidaProfesorField);
        panel.add(registroStatusButton);
        panel.add(registroStatusLabel);
        setContentPane(panel);
    }

    private void registrar() {
        // Obtener los números ingresados por el usuario
        int idProfessor = Integer.parseInt(idProfessorField.getText());
        String entradaProfesor = entradaProfesorField.getText();
        String salidaProfesor = salidaProfesorField.getText();
        // Calcular el registro
        String resultado = "Correcto";

        // Mostrar el resultado en la etiqueta correspondiente
        registroStatusLabel.setText("Se ha registrado al profesor con id " + idProfessor);

        // Guardar el registro en la base de datos
        guardarEnBaseDeDatos(idProfessor, entradaProfesor, salidaProfesor, resultado);
    }

    private void guardarEnBaseDeDatos(int idProfessor, String entradaProfesor, String salidaProfesor, String resultado) {
        try {
            // Conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/software_itsep2023", "root", "123");

            // Consulta para insertar el registro en la base de datos
            String query = "INSERT INTO RegistroProfesores (profesor_id, fecha_hora_entrada, fecha_hora_salida,registro_status) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, idProfessor);
            stmt.setString(2, entradaProfesor);
            stmt.setString(3, salidaProfesor);
            stmt.setString(4, resultado);
            stmt.executeUpdate();

            // Cerrar la conexión a la base de datos
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            // Manejo de excepciones
            JOptionPane.showMessageDialog(this, "Error al guardar el registro en la base de datos: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
