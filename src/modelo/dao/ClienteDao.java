package modelo.dao;

import modelo.entidades.Departamento;

import java.util.List;

public interface ClienteDao {

    void inserir(Departamento departamento);
    void atualizar(Departamento departamento);
    void deletarPorId(Integer id);
    Departamento buscarPorId(Integer id);
    List<Departamento> buscarTodos();
}
