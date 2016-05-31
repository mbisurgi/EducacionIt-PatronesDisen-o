package patrones.clase3.mediator;

import java.util.HashMap;

public class ServidorChat {
    public static void main(String[] args) {
        SalaDeChat sala = new SalaDeChatWebex();
        Participante p1 = new Participante("Maxi");
        Participante p2 = new Participante("Yani");
        
        sala.Registrar(p1);
        sala.Registrar(p2);
        
        p1.Enviar("Yani", "Hola, como te va?");
        p2.Enviar("Maxi", "Todo bien, vos?");
    }
}

abstract class SalaDeChat {
    public abstract void Registrar(Participante participante);
    public abstract void Enviar(String de, String para, String mensaje);
}

class SalaDeChatWebex extends SalaDeChat {
    HashMap<String, Participante> participantes = new HashMap<>();
    
    @Override
    public void Registrar(Participante participante) {
        if (!participantes.containsKey(participante.getNombre())) {
            participantes.put(participante.getNombre(), participante);
            participante.setSalaDeChat(this);
        }
    }
    
    @Override
    public void Enviar(String de, String para, String mensaje) {
        Participante destinatario = participantes.get(para);
        
        if (destinatario != null) {
            destinatario.Recibir(de, mensaje);
        }
    }


}

class Participante {
    private String nombre;
    private SalaDeChat salaDeChat;
    
    public Participante(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SalaDeChat getSalaDeChat() {
        return salaDeChat;
    }

    public void setSalaDeChat(SalaDeChat salaDeChat) {
        this.salaDeChat = salaDeChat;
    }
    
    public void Enviar(String para, String mensaje) {
        salaDeChat.Enviar(this.nombre, para, mensaje);
    }
    
    public void Recibir(String de, String mensaje) {
        System.out.println("De " + de + " para " + this.nombre + ". Mensaje: " + mensaje);
    }
}
