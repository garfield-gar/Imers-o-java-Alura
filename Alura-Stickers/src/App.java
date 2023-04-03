import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

	public static void main(String[] args) throws Exception {
	
		// Fazer uma conexão HTTP e buscar os top 250 filmes;
	
		String url="https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json";
		//String url="https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
		URI endereco = URI.create(url);
		var Client =HttpClient.newHttpClient();
		var	request	=HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = Client.send(request,BodyHandlers.ofString());
		String body = response.body();
		
		
		//Extrair só os dados que interessam (titulo, poster, classificaçao)
		JsonParser parser = new JsonParser();
		List<Map<String,String>>listaDeFilmes= parser.parse(body);
		
		
		// exibir e manipular os dados 

		var geradora =new Stickers();
		
		for(Map<String, String> filme: listaDeFilmes) {
			String urlImagem=filme.get("image");
			
			String titulo = filme.get("title");
			double classificacao= Double.parseDouble(filme.get("imDbRating"));
			
			String textoFigurinha;
			InputStream jpImagem;
			if(classificacao >=8.0) {
				textoFigurinha="Topzeira";
				jpImagem = new FileInputStream(new File("/home/charles/eclipse-workspace/Alura-Stickers/figurinhas/jpFeliz.png"));
			}else {
				textoFigurinha="hmmmmmm";
				jpImagem = new FileInputStream(new File("/home/charles/eclipse-workspace/Alura-Stickers/figurinhas/jpTriste.png"));
				
			}
			
			
			
			InputStream inputStream = new URL(urlImagem).openStream();
			String nomeArquivo = titulo + ".png";
			
			geradora.cria(inputStream, nomeArquivo, textoFigurinha, jpImagem);
			System.out.println("\u001b[m"+"\u001b[97m \u001b[104m Título: \u001b[m"+filme.get("title" ));
			System.out.println();
			
			
		}
		
	}

}


