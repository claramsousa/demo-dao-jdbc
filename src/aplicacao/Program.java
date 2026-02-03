package aplicacao;

import modelo.dao.DaoFabrica;
import modelo.dao.DepartamentoDao;
import modelo.entidades.Departamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        DepartamentoDao departamentoDao = DaoFabrica.criarDepartamentoDao();

        System.out.println("\nIniciando Testes em departamento: ");
        System.out.println("----------- TESTE 1: inserção ----------");
        Departamento departamento = new Departamento(null,"Shoes");
        departamentoDao.inserir(departamento);
        System.out.println("Departamento Inserido! Novo Id: " + departamento.getId());


        System.out.println("----------- TESTE 2: Buscar Por ID ----------");
        System.out.println(departamentoDao.buscarPorId(5));

        System.out.println("----------- TESTE 3: Buscar Todos ----------");
        List<Departamento> listaDep = new ArrayList<>();
        listaDep = departamentoDao.buscarTodos();
        for (Departamento dep : listaDep){
            System.out.println(dep);
        }

        System.out.println("----------- TESTE 4: Deletar ----------");
        System.out.println("DELETADO: " + departamentoDao.buscarPorId(departamento.getId()));
        departamentoDao.deletarPorId(departamento.getId());

        sc.close();
    }
}
