import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExemploEscrita {
    public static void main(String[] args) {
        Path caminhoDoArquivo = Paths.get("manipulacao-arquivos/texto.txt");

        try {
            String textoLido = Files.readString(caminhoDoArquivo);
            System.out.println("----- CONTÉUDO DO ARQUIVO -----");
            System.out.println(textoLido);
            System.out.println("-------------------------------");
        } catch (IOException e) {
            System.out.println("Erro ao ler no arquivo: " + e.getMessage());
        }
    }
}