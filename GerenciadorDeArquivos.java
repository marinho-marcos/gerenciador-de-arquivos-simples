import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class GerenciadorDeArquivos {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Path DIRETORIO_BASE = Paths.get("manipulacao-arquivos/documentos");

    public static void main(String[] args) {
        garantirExistenciaDiretorio();

        while(true) {
            exibirMenu();
            System.out.print("Informe uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); //limpar buffer

            switch(opcao) {
                case 1:
                    criarArquivo();
                    break;
                case 2:
                    lerArquivo();
                    break;
                case 3:
                    escreverEmArquivo();
                    break;
                case 4:
                    deletarArquivo();
                    break;
                case 5:
                    listarArquivos();
                    break;
                case 0:
                    System.out.println("Encerrando programa. até mais...");
                    scanner.close();
                    return;
            
                default:
                    System.out.println("Opção inválida. tente novamente.");
                    break;
            }
        }
    }
    

    private static void exibirMenu() {
        System.out.println("\n--- GERENCIADOR DE ARQUIVOS BÁSICO ---");
        System.out.println("Diretório de trabalho: " + DIRETORIO_BASE.toAbsolutePath());
        System.out.println("1. Criar um novo arquivo");
        System.out.println("2. Ler um arquivo");
        System.out.println("3. Escrever em um arquivo (adicionar conteúdo)");
        System.out.println("4. Deletar um arquivo");
        System.out.println("5. Listar arquivos no diretório");
        System.out.println("0. Sair");
    }

    private static void criarArquivo() {
        System.out.println("Informe o nome do arquivo que deseja criar: ");
        String nomeArquivo = scanner.nextLine();

        Path caminhoCompleto = DIRETORIO_BASE.resolve(nomeArquivo);

        try{
            if(Files.exists(caminhoCompleto)) {
                System.out.println("O arquivo já existe no diretório!\n");
            }else {
                Files.createFile(caminhoCompleto);
                System.out.println("Arquivo criado com sucesso!\n");
            }
        }catch (IOException e){
            System.out.println("Erro ao criar arquivo: " + e.getMessage());
        }
    }

    private static void lerArquivo() {
        System.out.println("Informe o nome do arquivo que deseja ler: ");
        String nomeArquivo = scanner.nextLine();

        Path caminhoCompleto = DIRETORIO_BASE.resolve(nomeArquivo);
        try {
            String conteudo = Files.readString(caminhoCompleto);
            System.out.println("----- CONTEÚDO DO ARQUIVO " + nomeArquivo + " -----");
            System.out.println(conteudo.isEmpty() ? "[O arquivo está vazio]" : conteudo);
            System.out.println("------------------------------------");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void escreverEmArquivo() {
        System.out.println("Informe o nome do arquivo no qual deseja escrever: ");
        String nomeArquivo = scanner.nextLine();

        Path caminhoCompleto = DIRETORIO_BASE.resolve(nomeArquivo);
        
        System.out.println("Infome o conteudo que deseja escrever: ");
        String conteudo = scanner.nextLine() + "\n";

        try {
            Files.writeString(caminhoCompleto, conteudo, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Conteudo escrito com sucesso no arquivo " + nomeArquivo + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao tentar escrever no arquivo: " + e.getMessage());
        }
    }

    private static void deletarArquivo() {
        System.out.println("Informe o nome do arquivo que deseja deletar: ");
        String nomeArquivo = scanner.nextLine();

        Path caminhoCompleto = DIRETORIO_BASE.resolve(nomeArquivo);

        try {
            if(Files.deleteIfExists(caminhoCompleto)) {
                System.out.println("Arquivo " + nomeArquivo + " deletado com sucesso!\n"); 
            } else {
                System.out.println("O arquivo não existe!\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao tentar deletar arquivo: " + e.getMessage());
        }
    }

    private static void listarArquivos() {
        System.out.println("\n--- ARQUIVOS EM '" + DIRETORIO_BASE + "' ---");
        // 'try-with-resources' garante que o Stream seja fechado automaticamente.
        try (Stream<Path> stream = Files.list(DIRETORIO_BASE)) {
            // Para cada Path no stream, pegamos apenas o nome do arquivo e imprimimos.
            stream.map(Path::getFileName)
                .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Erro ao listar os arquivos: " + e.getMessage());
        }
        System.out.println("-------------------------------------");
    }

    private static void garantirExistenciaDiretorio() {
            try {
                if(Files.notExists(DIRETORIO_BASE)) {
                    Files.createDirectories(DIRETORIO_BASE);
                    System.out.println("Diretório de trabalho '" + DIRETORIO_BASE + "' criado.");
                }
            } catch (IOException e) {
                System.out.println("ERRO CRÍTICO: Não foi possível criar o diretório de trabalho. Encerrando.");
                // Se não conseguimos criar a pasta principal, não dá pra continuar.
                System.exit(1);
            }
        }
}
