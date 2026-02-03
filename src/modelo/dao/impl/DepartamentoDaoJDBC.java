package modelo.dao.impl;
import db.DB;
import db.DbException;
import modelo.dao.DepartamentoDao;
import modelo.entidades.Departamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDaoJDBC implements DepartamentoDao{

    private Connection conn;

    public DepartamentoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserir(Departamento departamento) {

        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, departamento.getNome());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    departamento.setId(id);
                }
                DB.closeResultSet(rs);
            }
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizar(Departamento departamento) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE department SET Nome = ? WHERE Id = ? ");

            st.setString(1, departamento.getNome());
            st.setInt(2, departamento.getId());

            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletarPorId(Integer id) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");

            st.setInt(1, id);

            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Departamento buscarPorId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement ("SELECT * FROM department WHERE Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()){
                Departamento dep = new Departamento();
                dep.setId(rs.getInt("Id"));
                dep.setNome(rs.getString("Name"));

                return dep;
            }
            return null;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

    }

    @Override
    public List<Departamento> buscarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Departamento> lista = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT * FROM department ORDER BY Name");

            rs = st.executeQuery();

            while(rs.next()){
                Departamento departamento = new Departamento();
                departamento.setId(rs.getInt("Id"));
                departamento.setNome(rs.getString("Name"));
                lista.add(departamento);
            }
            return lista;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
