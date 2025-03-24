package model;
import java.io.Serializable;

public class PessoaFisica extends Pessoa implements Serializable {
    // Atributos adicionais
    private String cpf;
    private int idade;

    // Construtor padrão
    public PessoaFisica() {
    }

    // Construtor completo
    public PessoaFisica(int id, String nome, String cpf, int idade) {
        super(id, nome); // Chama o construtor da classe Pessoa
        this.cpf = cpf;
        this.idade = idade;
    }

    // Método exibir polimórfico (sobrescrito)
    @Override
    public void exibir() {
        super.exibir(); // Chama o método exibir da classe Pessoa
        System.out.println("CPF: " + cpf);
        System.out.println("Idade: " + idade);
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}