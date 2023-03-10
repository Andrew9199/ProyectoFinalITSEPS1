import javax.swing.SwingUtilities;

public class Main {

    /*
     * public static void main(String[] args){
     * Profesor Profesor1 = new Profesor("Alexis Sanchez", 21, "Ingles", "Mañana");
     * System.out.println("El nombre del profesor es " + Profesor1.nombre +
     * ", tiene " + Profesor1.edad + " años imparte la materia de " +
     * Profesor1.materia + " y su turno es por " + Profesor1.turno);
     * }
     */
    public static void main(String[] args) {
        // Crear la instancia de la ventana y mostrarla
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                EnSaRegistrator registrator = new EnSaRegistrator();
                registrator.setVisible(true);
            }
        });
    }
}
