package patrones.clase1;

public class CalculadoraSueldo {
    public static void main(String[] args) {
        Empleado gerente = new Gerente();
        Empleado vendedor = new Vendedor();
        Empleado vendedorComision = new VendedorComision(5);
        
        System.out.println(gerente.getSueldo());
        System.out.println(vendedor.getSueldo());
        System.out.println(vendedorComision.getSueldo());
    }
}

    abstract class Empleado {
        public abstract float getSueldo();
    }
    
    class Gerente extends Empleado {
        @Override
        public float getSueldo() {
            return 150000;
        }
    }
    
    class Vendedor extends Empleado {
        @Override
        public float getSueldo() {
            return 30000;
        }
    }
    
    class VendedorComision extends Vendedor {
        private int cantidadVentas;
        private float comision;
        
        public VendedorComision(int cantidadVentas) {
            this.cantidadVentas = cantidadVentas;
            this.comision = 500;
        }

        @Override
        public float getSueldo() {
            return super.getSueldo() + (cantidadVentas * comision); 
        }
    }
