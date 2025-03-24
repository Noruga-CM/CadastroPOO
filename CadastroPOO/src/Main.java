import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        int opcao;
        do {
            // Exibir menu
            System.out.println("\n--- Menu ---");
            System.out.println("1. Incluir");
            System.out.println("2. Alterar");
            System.out.println("3. Excluir");
            System.out.println("4. Exibir pelo ID");
            System.out.println("5. Exibir todos");
            System.out.println("6. Salvar dados");
            System.out.println("7. Recuperar dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1: // Incluir
                    incluirEntidade(scanner, repoFisica, repoJuridica);
                    break;

                case 2: // Alterar
                    alterarEntidade(scanner, repoFisica, repoJuridica);
                    break;

                case 3: // Excluir
                    excluirEntidade(scanner, repoFisica, repoJuridica);
                    break;

                case 4: // Exibir pelo ID
                    exibirPorId(scanner, repoFisica, repoJuridica);
                    break;

                case 5: // Exibir todos
                    exibirTodos(scanner, repoFisica, repoJuridica);
                    break;

                case 6: // Salvar dados
                    salvarDados(scanner, repoFisica, repoJuridica);
                    break;

                case 7: // Recuperar dados
                    recuperarDados(scanner, repoFisica, repoJuridica);
                    break;

                case 0: // Sair
                    System.out.println("Finalizando a execução...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    // Método para incluir uma entidade
    private static void incluirEntidade(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("\n--- Incluir ---");
        System.out.println("Escolha o tipo:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        if (tipo == 1) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine();

            PessoaFisica pessoa = new PessoaFisica(id, nome, cpf, idade);
            repoFisica.inserir(pessoa);
            System.out.println("Pessoa Física adicionada com sucesso!");
        } else if (tipo == 2) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();

            PessoaJuridica pessoa = new PessoaJuridica(id, nome, cnpj);
            repoJuridica.inserir(pessoa);
            System.out.println("Pessoa Jurídica adicionada com sucesso!");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    // Método para alterar uma entidade
    private static void alterarEntidade(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("\n--- Alterar ---");
        System.out.println("Escolha o tipo:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("ID da entidade a ser alterada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            PessoaFisica pessoa = repoFisica.obter(id);
            if (pessoa != null) {
                System.out.println("Dados atuais:");
                pessoa.exibir();

                System.out.print("Novo Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Nova Idade: ");
                int idade = scanner.nextInt();
                scanner.nextLine();

                PessoaFisica pessoaAtualizada = new PessoaFisica(id, nome, cpf, idade);
                repoFisica.alterar(pessoaAtualizada);
                System.out.println("Pessoa Física alterada com sucesso!");
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else if (tipo == 2) {
            PessoaJuridica pessoa = repoJuridica.obter(id);
            if (pessoa != null) {
                System.out.println("Dados atuais:");
                pessoa.exibir();

                System.out.print("Novo Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo CNPJ: ");
                String cnpj = scanner.nextLine();

                PessoaJuridica pessoaAtualizada = new PessoaJuridica(id, nome, cnpj);
                repoJuridica.alterar(pessoaAtualizada);
                System.out.println("Pessoa Jurídica alterada com sucesso!");
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    // Método para excluir uma entidade
    private static void excluirEntidade(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("\n--- Excluir ---");
        System.out.println("Escolha o tipo:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("ID da entidade a ser excluída: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            repoFisica.excluir(id);
            System.out.println("Pessoa Física excluída com sucesso!");
        } else if (tipo == 2) {
            repoJuridica.excluir(id);
            System.out.println("Pessoa Jurídica excluída com sucesso!");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    // Método para exibir uma entidade pelo ID
    private static void exibirPorId(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("\n--- Exibir pelo ID ---");
        System.out.println("Escolha o tipo:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("ID da entidade: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            PessoaFisica pessoa = repoFisica.obter(id);
            if (pessoa != null) {
                pessoa.exibir();
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else if (tipo == 2) {
            PessoaJuridica pessoa = repoJuridica.obter(id);
            if (pessoa != null) {
                pessoa.exibir();
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    // Método para exibir todas as entidades
    private static void exibirTodos(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("\n--- Exibir Todos ---");
        System.out.println("Escolha o tipo:");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        if (tipo == 1) {
            System.out.println("Pessoas Físicas Cadastradas:");
            for (PessoaFisica pessoa : repoFisica.obterTodos()) {
                pessoa.exibir();
                System.out.println("------");
            }
        } else if (tipo == 2) {
            System.out.println("Pessoas Jurídicas Cadastradas:");
            for (PessoaJuridica pessoa : repoJuridica.obterTodos()) {
                pessoa.exibir();
                System.out.println("------");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    // Método para salvar dados
    private static void salvarDados(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("\n--- Salvar Dados ---");
        System.out.print("Digite o prefixo dos arquivos: ");
        String prefixo = scanner.nextLine();

        try {
            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    // Método para recuperar dados
    private static void recuperarDados(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("\n--- Recuperar Dados ---");
        System.out.print("Digite o prefixo dos arquivos: ");
        String prefixo = scanner.nextLine();

        try {
            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar dados: " + e.getMessage());
        }
    }
}