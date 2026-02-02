package aplicacao;

import modelo.dao.DaoFabrica;
import modelo.dao.VendedorDao;
import modelo.entidades.Vendedor;


public class Main {
    public static void main (String[] args){

        VendedorDao vendedorDao = DaoFabrica.criarVendedorDao();

        System.out.println("----- TESTE 1: vendedor buscar por Id -----");
        Vendedor vendedor = vendedorDao.buscarPorId(3);
        System.out.println(vendedor);

    }
}
