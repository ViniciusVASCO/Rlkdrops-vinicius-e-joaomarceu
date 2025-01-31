package vinicius.joaomarceu.model.entities;

public class Tenis {

    private int id;
    private int estoque;
    private String nome;
    private String descricao;
    private float valor;
    private Categoria categoria;

    public Tenis(int id, int estoque, String nome, String descricao, float valor, Categoria categoria) {
        this.id = id;
        this.estoque = estoque;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public Tenis(int estoque, String nome, String descricao, float valor, Categoria categoria) {
        this.estoque = estoque;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getEstoque() {
        return estoque;
    }
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "["+ nome + "]";
    }
}
