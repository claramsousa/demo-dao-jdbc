package modelo.dao.impl;

import modelo.dao.VendedorDao;
import modelo.entidades.Vendedor;

import java.util.List;

public class VendedorDaoJDBC implements VendedorDao {
    @Override
    public void inserir(Vendedor vendedor) {

    }

    @Override
    public void atualizar(Vendedor vendedor) {

    }

    @Override
    public void deletarPorId(Integer id) {

    }

    @Override
    public Vendedor buscarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Vendedor> buscarTodos() {
        return List.of();
    }
}
