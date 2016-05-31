package patrones.clase3.memento;

public class RecuperarVersionPrevia {
    public static void main(String[] args) {
        Orden orden = new Orden(1000);
        Instante instante = orden.crearInstante();
        
        try {
            orden.setMonto(1500);
            
            throw new Exception();
        } catch (Exception e) {
            //Si hubo algun fallo, vuelvo al instante previo a realizar los cambios
            orden.recuperarInstante(instante);
        }
        
        System.out.println("El monto de la orden es: " + orden.getMonto());
    }  
}

//Definir una clase Instante que guarde los valores de una clase en algun momento en particular para poder recuperar los valores en caso de alguna falla
class Instante {
    private double monto;
    
    public Instante(double monto) {
        this.monto = monto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    } 
}

//Definir una clase que utilice dicha clase instante
class Orden {
    private double monto;
    
    public Orden(double monto) {
        this.monto = monto;
    }
    
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    } 
    
    public Instante crearInstante() {
        return new Instante(monto);
    }
    
    public void recuperarInstante(Instante instante) {
        this.monto = instante.getMonto();
    }
}