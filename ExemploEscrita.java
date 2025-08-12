import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExemploEscrita {
    public static void main(String[] args) {
        Path caminhoDoArquivo = Paths.get("manipulacao-arquivos/texto.txt");
        String conteudo = "Hello mzrrrrrrrrrrrrrrrrrrrrrrrrrrr!";

        try {
            Files.writeString(caminhoDoArquivo, conteudo);
            System.out.println("\nConteúdo escrito com sucesso!\n");

            String textoLido = Files.readString(caminhoDoArquivo);
            System.out.println("----- CONTÉUDO DO ARQUIVO -----");
            System.out.println(textoLido);
            System.out.println("-------------------------------");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }


        //verifica se o arquivo existe
        boolean arquivosExiste = Files.exists(caminhoDoArquivo);
        System.out.println("\nO arquivos existe? " + arquivosExiste);

        //criando diretorio (caso nao exista)
        Path caminhoPasta = Paths.get("manipulacao-arquivos/nova-pasta");

        try {
            if(!Files.exists(caminhoPasta)) {
            Files.createDirectory(caminhoPasta);
            System.out.println("\nPasta criada com sucesso!");

            }else {
                System.out.println("\nA pasta ja existe!");
            }
        } catch (IOException e) {
            System.out.println("\nErro ao criar pasta> " + e.getMessage());
        }

    }
}