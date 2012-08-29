package es.icane.metadatos.test;

import es.icane.metadatos.Cliente;

/**
 *
 * @author Alejandro Villar <contacto@alejandro-villar.es>
 */
public class NodoTest {
    public static void main(String[] args){
        Cliente cliente = new Cliente("http://192.168.0.133:8081/metadatos/api");
        System.out.println(cliente.getNodo(138));
    }
}
