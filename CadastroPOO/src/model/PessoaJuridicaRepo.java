package model;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaRepo implements Serializable {
    // Lista para armazenar objetos do tipo PessoaJuridica
    private List<PessoaJuridica> pessoasJuridicas;

    // Construtor
    public PessoaJuridicaRepo() {
        this.pessoasJuridicas = new ArrayList<>();
    }

    // Método para inserir uma PessoaJuridica
    public void inserir(PessoaJuridica pessoaJuridica) {
        pessoasJuridicas.add(pessoaJuridica);
    }

    // Método para alterar uma Pesso    aJuridica existente
    public void alterar(PessoaJuridica pessoaJuridicaAtualizada) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            if (pessoasJuridicas.get(i).getId() == pessoaJuridicaAtualizada.getId()) {
                pessoasJuridicas.set(i, pessoaJuridicaAtualizada); // Substitui a entidade existente
                break;
            }
        }
    }

    // Método para excluir uma PessoaJuridica pelo ID
    public void excluir(int id) {
        pessoasJuridicas.removeIf(pessoa -> pessoa.getId() == id);
    }

    // Método para obter uma PessoaJuridica pelo ID
    public PessoaJuridica obter(int id) {
        for (PessoaJuridica pessoa : pessoasJuridicas) {
            if (pessoa.getId() == id) {
                return pessoa;
            }
        }
        return null; // Retorna null se não encontrar
    }

    // Método para obter todas as pessoas jurídicas (cópia defensiva)
    public List<PessoaJuridica> obterTodos() {
        return new ArrayList<>(pessoasJuridicas); // Retorna uma cópia da lista
    }
    
     // Método para persistir os dados em um arquivo binário (ecoando exceções)
    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            outputStream.writeObject(pessoasJuridicas); // Serializa a lista de pessoas jurídicas
            System.out.println("Dados de Pessoa Jurídica persistidos com sucesso em " + nomeArquivo);
        }
    }

    // Método para recuperar os dados de um arquivo binário (ecoando exceções)
    @SuppressWarnings("unchecked")
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoasJuridicas = (List<PessoaJuridica>) inputStream.readObject(); // Desserializa a lista de pessoas jurídicas
            System.out.println("Dados de Pessoa Jurídica recuperados com sucesso de " + nomeArquivo);
        }
    }
}