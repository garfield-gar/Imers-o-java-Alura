import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Stickers {

	public void cria(InputStream inputStream, String nomeArquivo, String texto,InputStream inputStreamSobreposicao) throws Exception {
		
		//leitura da imagem
	BufferedImage imagemOriginal = ImageIO.read(inputStream);
		
		//cria nova imagem comtraparencia e com tamanho novo
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura +200;
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura,BufferedImage.TRANSLUCENT);
		
		//copiar a imagem originalpra nova (em memoria)
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal,0,0,null);
		
		BufferedImage imagemSobreposicao = ImageIO.read(inputStreamSobreposicao);
		int posicaoImagemSobreposicaoY = novaAltura -imagemSobreposicao.getHeight();
		graphics.drawImage(imagemSobreposicao, 0, posicaoImagemSobreposicaoY, null);
		
		//configurar a fonte 
		var fonte = Font.createFont(Font.TRUETYPE_FONT, new File("/home/charles/eclipse-workspace/Alura-Stickers/fonte/impact/impact.ttf")).deriveFont(80f);
		graphics.setColor(Color.YELLOW);
		graphics.setFont(fonte);
		
		//escrever uma frase na nova imagem
		graphics.drawString("TOPZEIRA",450,novaAltura - 100);
		// escrever a imagem nova em um arquivo
		ImageIO.write(novaImagem, "png",new File("/home/charles/eclipse-workspace/Alura-Stickers/saida/" + nomeArquivo) );
	
		
		
		
	
	}


	
	
}