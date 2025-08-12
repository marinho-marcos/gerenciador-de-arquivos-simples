import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExemploPath {
    public static void main(String[] args) {
        Path caminhoDoArquivo = Paths.get("texto.txt");

        System.out.println("nome do arquivo: " + caminhoDoArquivo.getFileName());
        System.out.println("nome completo do caminho: " + caminhoDoArquivo.toAbsolutePath());
    }
}