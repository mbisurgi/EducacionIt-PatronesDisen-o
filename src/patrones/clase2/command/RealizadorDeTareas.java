package patrones.clase2.command;

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
        
        ITask batch = new BatchTask(tasks);
        batch.execute();
    }
}

//Definir una interfaz con un metodo ejecutar
//Es el comando
interface ITask {
    public void execute();
}

//Definir clases que implementan dicha interfaz y que agrupan varios comandos o metodos, por ejemplo, un backup, un restore, o un trabajo completo
//Estos son los comandos concretos
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

class BatchTask implements ITask {
    private List<ITask> tasks;
            
    public BatchTask(List<ITask> tasks) {
        this.tasks = tasks;
    }        
    
    @Override
    public void execute() {
        for (ITask task : tasks) {
            task.execute();
        }
    }
}

//Definir una interfaz con los comandos a realizar por algo en particular
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