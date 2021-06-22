

package aleatorio;

import java.util.Scanner;
import java.io.*;

public class Aleatorio {
    
    static RandomAccessFile archivo;
    
    static void escribir(String nombre, int edad, char sexo) throws IOException {
        archivo.seek(archivo.length()); //el puntero se posiciona al final
        archivo.writeUTF(nombre);
        archivo.writeInt(edad);
        archivo.writeChar(sexo);
    }
    
    static boolean buscarPersona(String nombre) throws IOException { 
        archivo.seek(0);
        while(archivo.getFilePointer() < archivo.length()) {
            if(archivo.readUTF().equals(nombre))
                return true;
            archivo.skipBytes(6);
        }
        return false;
    }
    
    static void imprimir() throws IOException {
        archivo.seek(0);
        while(archivo.getFilePointer() < archivo.length()) {
            System.out.println(archivo.readUTF());
            System.out.println(archivo.readInt());
            System.out.println(archivo.readChar());
        }
    }

    public static void main(String[] args) {
        try {
            //r
            //rw
            archivo = new RandomAccessFile("C:\\Users\\LENOVO\\OneDrive\\Documentos\\NetBeansProjects\\Aleatorio1\\build\\classes\\aleatorio1\\ficheros/Personas.dat", "rw");
        } catch(IOException e) {
           
        }
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.print("1. Escribir en el archivo binario\n"
                    + "2. Buscar persona en el archivo\n"
                    + "3. Mostrar todos los datos guardados\n"
                    + "4. Salir\n"
                    + "Escriba la opcion aqui: ");
            opcion = scanner.nextInt();
            System.out.println();
            switch(opcion) {
                case 1: {
                    String nombre;
                    int edad;
                    char sexo;
                    System.out.print("Escriba su nombre: ");
                    nombre = scanner.next();
                    System.out.print("Escriba su edad: ");
                    edad = scanner.nextInt();
                    System.out.print("Escriba su sexo: ");
                    sexo = scanner.next().charAt(0);
                    try {
                        escribir(nombre, edad, sexo);
                    } catch(IOException e) {
                        
                    }
                    System.out.println("Se han ingresado los datos correctamente!");
                    break;
                }
                case 2: {
                    String nombre;
                    System.out.print("Escriba el nombre que desea buscar: ");
                    nombre = scanner.next();
                    try {
                        if(buscarPersona(nombre))
                            System.out.println("Se ha encontrado el nombre en el archivo");
                        else
                            System.out.println("No se ha encontrado el nombre en el archivo");
                    } catch(IOException e) {
                        
                    }
                    
                    break;
                }
                case 3: {
                    try {
                        imprimir();
                    } catch(IOException e) {
                        
                    }
                    break;
                }
                default: {
                    if(opcion != 4)
                        System.out.println("Opcion no valida");
                }
            }
            System.out.println();
        } while(opcion != 4);
    }
}
