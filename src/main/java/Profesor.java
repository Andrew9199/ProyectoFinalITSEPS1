public class Profesor extends Person {
    String materia;
    String turno;

    public Profesor(String nombre, Integer edad, String materia, String turno){
        super(nombre, edad);
        this.materia = materia;
        this.turno = turno;
    }
}
