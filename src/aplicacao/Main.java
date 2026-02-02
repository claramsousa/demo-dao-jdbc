package aplicacao;

import modelo.dao.DaoFabrica;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

import java.util.Date;

public class Main {
    public static void main (String[] args){

        VendedorDao vendedorDao = DaoFabrica.criarVendedorDao();

        Vendedor vendedor = vendedorDao.buscarPorId(3);
        System.out.println(vendedor);

    }
}
