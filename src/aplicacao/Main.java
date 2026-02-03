package aplicacao;

import modelo.dao.DaoFabrica;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

import java.lang.classfile.constantpool.AnnotationConstantValueEntry;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main (String[] args){

        VendedorDao vendedorDao = DaoFabrica.criarVendedorDao();

        System.out.println("----- TESTE 1: vendedor buscar por Id -----");
        Vendedor vendedor = vendedorDao.buscarPorId(3);
        System.out.println(vendedor);

        System.out.println("\n----- TESTE 2: vendedor buscar por Departamento -----");
        Departamento departamento = new Departamento(2, null);
        List<Vendedor> lista = vendedorDao.buscarPorDepartamento(departamento);
        for (Vendedor vend : lista){
            System.out.println(vend);
        }

        System.out.println("\n----- TESTE 3: vendedor buscar Todos -----");
        lista = vendedorDao.buscarTodos();
        for (Vendedor vend : lista){
            System.out.println(vend);
        }

        System.out.println("\n----- TESTE 4: inserir vendedor -----");
        Vendedor vendedor1 = new Vendedor(null, "greg", "greg@gmail.com", new Date(), 4000.00, departamento);
        vendedorDao.inserir(vendedor1);
        System.out.println("Inserido! novo Id: " + vendedor1.getId());

        System.out.println("\n----- TESTE 5: atualizar vendedor -----");
        vendedor = vendedorDao.buscarPorId(1);
        vendedor.setNome("Marta Waine");
        vendedorDao.atualizar(vendedor);
        System.out.println("Atualização completa!");
    }
}
