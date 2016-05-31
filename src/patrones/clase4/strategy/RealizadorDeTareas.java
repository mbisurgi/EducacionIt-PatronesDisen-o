package patrones.clase4.strategy;

import java.util.ArrayList;
import java.util.List;

public class RealizadorDeTareas {
    public static void main(String[] args) {
        IShell bash = new Bash();
        List<ITask> tasks = new ArrayList<>();
        
        ITask backup = new BackupTask(bash);
        tasks.add(backup);
        
        ITask restore = new RestoreTask(bash);
        tasks.add(restore);
        
        //Al constructor le paso una instancia de la estrategia concreta, no de la estrategia global
        ITask batch = new BatchTask(tasks, new SerialModel());
        batch.execute();
    }
}

//Definir una estrategia en funciones con caracteristicas similares
abstract class ExecutionMode {
    public abstract void run(List<ITask> tasks);
}

//Definir las estrategias concretas a ejecutarse en tiempo de ejecucion
class SerialModel extends ExecutionMode {
    @Override
    public void run(List<ITask> tasks) {
        tasks.stream().forEach(x-> x.execute());
    }
}

class ParallelMode extends ExecutionMode {
    @Override
    public void run(List<ITask> tasks) {
        tasks.parallelStream().forEach(x-> x.execute());
    }
}

interface ITask {
    public void execute();
}

class BackupTask implements ITask {
    private IShell shell;

    public BackupTask(IShell shell) {
        this.shell = shell;
    }
    
    @Override
    public void execute() {
        shell.zip();
        shell.move();
    }
}

class RestoreTask implements ITask {
    private IShell shell;

    public RestoreTask(IShell shell) {
        this.shell = shell;
    }
    
    @Override
    public void execute() {
        shell.move();
        shell.unzip();
    }
}

//En tiempo de ejecucion paso la estrategia concreta que quiero realizar y se ejecuta el algoritmo correspondiente
class BatchTask implements ITask {
    private List<ITask> tasks;
    private ExecutionMode executionMode;
            
    public BatchTask(List<ITask> tasks, ExecutionMode executionMode) {
        this.tasks = tasks;
        this.executionMode = executionMode;
    }        
    
    @Override
    public void execute() {
        executionMode.run(tasks);
    }
}

interface IShell {
    public void copy();
    public void move();
    public void zip();
    public void unzip();
}

class Dos implements IShell {
    @Override
    public void copy() {
        
    }

    @Override
    public void move() {
        
    }

    @Override
    public void zip() {
        
    }

    @Override
    public void unzip() {
        
    }    
}

class Bash implements IShell {
    @Override
    public void copy() {
        System.out.println("Copiando archivos...");
    }

    @Override
    public void move() {
        System.out.println("Moviendo archivos...");
    }

    @Override
    public void zip() {
        System.out.println("Zipeando archivos...");
    }

    @Override
    public void unzip() {
        System.out.println("Deszipeando archivos...");
    }  
}