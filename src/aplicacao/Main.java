package aplicacao;

import entidades.Departamento;
import entidades.Vendedor;

import java.util.Date;

public class Main {
    public static void main (String[] args){

        Departamento departamento = new Departamento(1, "livros");
        System.out.println(departamento);

        Vendedor vendedor = new Vendedor(21, "Bob", "bob@gmail.com", new Date(), 3000.00, departamento);
        System.out.println(vendedor);
    }
}
