package patrones.laboratorio;

public class MainBanco {
    public static void main(String[] args) {
        System.out.println(Banco.getInstancia().toString());
    }
}

class Banco {
    private static Banco instancia = null;
    
    private String nombre;
    private String calle;
    private int numero;
    private String telefono;
    
    private Banco() {
        this.nombre = "Banco IT";
        this.calle = "Lavalle";
        this.numero = 648;
        this.telefono = "4328-0457";
    }
    
    public static Banco getInstancia() {
        if (instancia == null) {
            instancia = new Banco();
        }
        
        return instancia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCalle() {
        return calle;
    }

    public int getNumero() {
        return numero;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "Banco{" + "nombre=" + nombre + ", calle=" + calle + ", numero=" + numero + ", telefono=" + telefono + '}';
    }  
}
