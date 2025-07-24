import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.net.InetSocketAddress;
import java.nio.file.Files;

public class ServidorHttp {

    public static void main(String[] args) throws IOException {
        int porta = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(porta), 0);

        // Define o diretório base onde estão os arquivos HTML
        final String baseDir = System.getProperty("user.dir") + "/src/main/resources/";

        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String uri = exchange.getRequestURI().getPath();
                String caminhoArquivo = baseDir + (uri.equals("/") ? "index.html" : uri.substring(1));

                File arquivo = new File(caminhoArquivo);

                if (!arquivo.exists() || arquivo.isDirectory()) {
                    String respostaErro = "Arquivo não encontrado!";
                    exchange.sendResponseHeaders(404, respostaErro.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(respostaErro.getBytes());
                    os.close();
                    return;
                }

                byte[] bytesArquivo = Files.readAllBytes(arquivo.toPath());
                exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
                exchange.sendResponseHeaders(200, bytesArquivo.length);

                OutputStream os = exchange.getResponseBody();
                os.write(bytesArquivo);
                os.close();
            }
        });

        server.setExecutor(null);
        server.start();

        System.out.println("✅ Servidor rodando em: http://localhost:" + porta);
    }
}
