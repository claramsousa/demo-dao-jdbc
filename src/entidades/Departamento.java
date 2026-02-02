package entidades;

import java.io.Serializable;
import java.util.Objects;

public class Departamento implements Serializable {

    private Integer Id;
    private String nome;

    public Departamento(Integer id, String nome) {
        Id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }

    @Override
    public String toString() {
        return "entidades.Departamento{" +
                "Id=" + Id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
