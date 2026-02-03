package modelo.dao.impl;

import db.DB;
import db.DbException;
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendedorDaoJDBC implements VendedorDao {

    private Connection conn;

    public VendedorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Vendedor vendedor) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO  seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, vendedor.getNome());
            st.setString(2, vendedor.getEmail());
            st.setDate(3, new java.sql.Date(vendedor.getDataNascimento().getTime()));
            st.setDouble(4, vendedor.getSalarioBase());
            st.setInt(5, vendedor.getDepartamento().getId());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    vendedor.setId(id);

                }

                DB.closeResultSet(rs);

            } else {
                throw new DbException("Erro inesperado! nenhuma linha afetada");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizar(Vendedor vendedor) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE seller "
                            + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                            + "WHERE Id = ?"
            );

            st.setString(1, vendedor.getNome());
            st.setString(2, vendedor.getEmail());
            st.setDate(3, new Date(vendedor.getDataNascimento().getTime()));
            st.setDouble(4, vendedor.getSalarioBase());
            st.setInt(5, vendedor.getDepartamento().getId());
            st.setInt(6, vendedor.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

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
                Departamento dep = instanciarDepartamento(rs);

                Vendedor vend = instanciarVendedor(rs, dep);
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
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "ORDER BY Name");

            rs = st.executeQuery();

            List<Vendedor> lista = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while(rs.next()){

                Departamento dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = instanciarDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                Vendedor vend = instanciarVendedor(rs, dep);
                lista.add(vend);
            }
            return lista;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public List<Vendedor> buscarPorDepartamento(Departamento departamento) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "WHERE DepartmentId = ? "
                            + "ORDER BY Name");

            st.setInt(1, departamento.getId());
            rs = st.executeQuery();

            List<Vendedor> lista = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while(rs.next()){

                Departamento dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = instanciarDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                Vendedor vend = instanciarVendedor(rs, dep);
                lista.add(vend);
            }
            return lista;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Vendedor instanciarVendedor(ResultSet rs, Departamento dep) throws SQLException {
        Vendedor vend = new Vendedor();
        vend.setId(rs.getInt("Id"));
        vend.setNome(rs.getString("Name"));
        vend.setEmail(rs.getString("Email"));
        vend.setDataNascimento(rs.getDate("BirthDate"));
        vend.setSalarioBase(rs.getDouble("BaseSalary"));
        vend.setDepartamento(dep);
        return vend;
    }

    private Departamento instanciarDepartamento(ResultSet rs) throws SQLException {
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setNome(rs.getString("DepName"));
        return dep;
    }
}
