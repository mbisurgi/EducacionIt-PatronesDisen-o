package patrones.clase1.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BalanceadorDeCargas {
    public static void main(String[] args) {
        Server server = LoadBalancer.getInstancia().getServer();
        
        System.out.println(server.getDireccion());
    }
}

class LoadBalancer {
    //Definir un atributo estatico y privado que guarda la instancia
    private static LoadBalancer instancia = null;
    //Definir un atributo estatico y privado bobo para utilizar synchronized
    private static Object lock = new Object();
    private List<Server> servers = new ArrayList<>();   
    
    //Definir un constructor privado, para evitar el posible uso de new()
    private LoadBalancer() {
        servers.add(new Server("192.168.0.1"));
        servers.add(new Server("192.168.0.2"));
        servers.add(new Server("192.168.0.3"));
        servers.add(new Server("192.168.0.4"));
        servers.add(new Server("192.168.0.5"));
    }
    
    //Definir un metodo estatico y publico que devuelva la instancia existente o cree una nueva si todavia no existe
    //Utilizando el synchronized hacemos al singleton viable en escenarios concurrentes
    public static LoadBalancer getInstancia() {
        if (instancia == null) {
            synchronized(lock) {
                if (instancia == null) {
                    instancia = new LoadBalancer();
                }
            }
        }
        
        return instancia;
    }
    
    public Server getServer() {
        Random random = new Random();
        int i = random.nextInt(servers.size());
        
        return servers.get(i);
    }
}

class Server {
    private String direccion;
    
    public Server(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }
}
