package vinicius.joaomarceu.model.entities;

public class Vendedor {
    private String cpf;
    private String nome;

    public Vendedor(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
    public String toString() {
        return "["+ nome + "]";
  }
}