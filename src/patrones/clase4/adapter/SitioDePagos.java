package patrones.clase4.adapter;

public class SitioDePagos {
    public static void main(String[] args) {
        IPayment transaccion = new MercadoPagoPayment();
        
        transaccion.pay();
    }
}

//Definir una interfaz comun para que el cliente siempre ejecute el mismo metodo sin importar que API, o SDK, etc. se utiliza
interface IPayment {
    public void pay();
}

//Definir clases que implementen dicha interfaz. Estas clases son las que instanciaran por ejemplo una clase del SDK externo y llamaran a los metodos correspondientes
class MercadoPagoPayment implements IPayment {
    MercadoPago mercadoPago = new MercadoPago();
    
    @Override
    public void pay() {
        mercadoPago.payOrder();
        System.out.println("Pagando con Mercado Pago...");
    }  
}

class PaypalPayment implements IPayment {
    Paypal paypal = new Paypal();

    @Override
    public void pay() {
        paypal.payTransaction();
        System.out.println("Pagando con Paypal...");
    }
}

//Tengo dos SDK o APIs distintas
//API de Mercado Pago
class MercadoPago {
    public void payOrder() {
    
    }
}

//API de Paypal
class Paypal {
    public void payTransaction() {
        
    }
}
