import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExemploCopia
{
	public static void main(String[] args) {
		Path original = Paths.get("manipulacao-arquivos/copia/meu-primeiro-arquivo.txt");
        Path copia = Paths.get("manipulacao-arquivos/copia/copia-do-meu-arquivo.txt");
        try {
            Files.writeString(original, "teste de copia de arquivo");

            Files.copy(original, copia);
            System.out.println("Arquivo copiado!");
        } catch (IOException e) {
            System.out.println("Erro ao copiar: " + e.getMessage());
        }
	}
}

