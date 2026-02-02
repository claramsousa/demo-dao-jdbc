package modelo.dao.impl;

import db.DB;
import db.DbException;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VendedorDaoJDBC implements VendedorDao {

    private Connection conn;

    public VendedorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

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
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Departamento dep = new Departamento();
                dep.setId(rs.getInt("DepartmentId"));
                dep.setNome(rs.getString("DepName"));

                Vendedor vend = new Vendedor();
                vend.setId(rs.getInt("Id"));
                vend.setNome(rs.getString("Name"));
                vend.setEmail(rs.getString("Email"));
                vend.setDataNascimento(rs.getDate("BirthDate"));
                vend.setDepartamento(dep);
                return vend;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public List<Vendedor> buscarTodos() {
        return List.of();
    }
}
