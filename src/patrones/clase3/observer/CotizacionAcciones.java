package patrones.clase3.observer;

import java.util.ArrayList;
import java.util.List;

public class CotizacionAcciones {
    public static void main(String[] args) {
        Inversor inv1 = new Inversor("Maximiliano Bisurgi");
        Inversor inv2 = new Inversor("Claudio Bisurgi");
        
        Accion acc1 = new Accion("GOOG", 100);
        
        acc1.agregar(inv1);
        acc1.agregar(inv2);
        
        acc1.setValor(99);
        acc1.setValor(98);
        acc1.setValor(101);
    }
}

//Definir una interfaz observable que deberan implementar las clases que deban informar a otras sobre sus cambios
interface Observable {
    public void agregar(Observador observador);
    public void remover(Observador observador);
    public void notificar();
}

//Definir una interfaz observador, la cual debera tener un metodo actualizar a implementar por los observadores
interface Observador {
    public void actualizar(Accion accion);
}

class Inversor implements Observador {
    private String nombre;
    private Accion accion;

    public Inversor(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }
    
    @Override
    public void actualizar(Accion accion) {
        this.accion = accion;
        
        System.out.println("Soy " + this.nombre + " y estoy enterado del cambio del valor de la accion a " + this.accion.getValor());
    }  
}

class Accion implements Observable {
    private String nombre;
    private float valor;
    private List<Observador> observadores = new ArrayList<>();
    
    public Accion(String nombre, float valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
        
        notificar();
    }

    @Override
    public void agregar(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void remover(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificar() {
        for (Observador observador : observadores) {
            observador.actualizar(this);
        }
    }
}