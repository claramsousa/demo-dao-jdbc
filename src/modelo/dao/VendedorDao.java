package modelo.dao;

import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

import java.util.List;

public interface VendedorDao {
    void inserir(Vendedor vendedor);
    void atualizar(Vendedor vendedor);
    void deletarPorId(Integer id);
    Vendedor buscarPorId(Integer id);
    List<Vendedor> buscarTodos();
    List<Vendedor> buscarPorDepartamento(Departamento departamento);

}
