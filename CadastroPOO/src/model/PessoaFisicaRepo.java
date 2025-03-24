package model;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaRepo implements Serializable {
    // Lista para armazenar objetos do tipo PessoaFisica
    private List<PessoaFisica> pessoasFisicas;

    // Construtor
    public PessoaFisicaRepo() {
        this.pessoasFisicas = new ArrayList<>();
    }

    // Método para inserir uma PessoaFisica
    public void inserir(PessoaFisica pessoaFisica) {
        pessoasFisicas.add(pessoaFisica);
    }

    // Método para alterar uma PessoaFisica existente
    public void alterar(PessoaFisica pessoaFisicaAtualizada) {
        for (int i = 0; i < pessoasFisicas.size(); i++) {
            if (pessoasFisicas.get(i).getId() == pessoaFisicaAtualizada.getId()) {
                pessoasFisicas.set(i, pessoaFisicaAtualizada); // Substitui a entidade existente
                break;
            }
        }
    }

    // Método para excluir uma PessoaFisica pelo ID
    public void excluir(int id) {
        pessoasFisicas.removeIf(pessoa -> pessoa.getId() == id);
    }

    // Método para obter uma PessoaFisica pelo ID
    public PessoaFisica obter(int id) {
        for (PessoaFisica pessoa : pessoasFisicas) {
            if (pessoa.getId() == id) {
                return pessoa; // Retorna a pessoa física encontrada
            }
        }
        return null; // Retorna null se não encontrar
    }

    // Método para obter todas as pessoas físicas (cópia defensiva)
    public List<PessoaFisica> obterTodos() {
        return new ArrayList<>(pessoasFisicas); // Retorna uma cópia da lista
    }
    
      // Método para persistir os dados em um arquivo binário (ecoando exceções)
    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            outputStream.writeObject(pessoasFisicas); // Serializa a lista de pessoas físicas
            System.out.println("Dados de Pessoa Física persistidos com sucesso em " + nomeArquivo);
        }
    }

    // Método para recuperar os dados de um arquivo binário (ecoando exceções)
    @SuppressWarnings("unchecked")
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoasFisicas = (List<PessoaFisica>) inputStream.readObject(); // Desserializa a lista de pessoas físicas
            System.out.println("Dados de Pessoa Física recuperados com sucesso de " + nomeArquivo);
        }
    }

    
}