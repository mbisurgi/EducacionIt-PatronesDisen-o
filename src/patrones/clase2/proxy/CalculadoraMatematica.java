package patrones.clase2.proxy;

public class CalculadoraMatematica {
    public static void main(String[] args) {
        ICalculadora calculadora = new CalculadoraProxy();
        
        System.out.println(calculadora.sumar(2, 5));
    }
}

//Definir una interfaz que va a dar acceso al objeto concreto y al objeto proxy
interface ICalculadora {
    public float sumar(float a, float b);
    public float restar(float a, float b);
    public float multiplicar(float a, float b);
    public float dividir(float a, float b);
}

//Definir el objeto concreto
class Calculadora implements ICalculadora {
    @Override
    public float sumar(float a, float b) {
        return a + b;
    }

    @Override
    public float restar(float a, float b) {
        return a - b;
    }

    @Override
    public float multiplicar(float a, float b) {
        return a * b;
    }

    @Override
    public float dividir(float a, float b) {
        return a / b;
    }
}

//Definir el objeto proxy que es el que tiene acceso al objeto concreto
//El cliente va a conocer solo al proxy y trabajar con dicho proxy
class CalculadoraProxy implements ICalculadora {
    private ICalculadora calculadora = new Calculadora();
    
    @Override
    public float sumar(float a, float b) {
        return calculadora.sumar(a, b);
    }

    @Override
    public float restar(float a, float b) {
        return calculadora.restar(a, b);
    }

    @Override
    public float multiplicar(float a, float b) {
        return calculadora.multiplicar(a, b);
    }

    @Override
    public float dividir(float a, float b) {
        return calculadora.dividir(a, b);
    }
}
