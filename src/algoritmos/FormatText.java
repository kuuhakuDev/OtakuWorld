package algoritmos;

public class FormatText {

	public static String utf8(String text) {
		
		while(text.indexOf("Ã") != -1) {
			text = text.replaceFirst("Ã±", "ñ");
			text = text.replaceFirst("Ã³", "ó");
			text = text.replaceFirst("Ãº", "ú");
			text = text.replaceFirst("Ã­", "í");
			text = text.replaceFirst("Ã¡", "á");
			text = text.replaceFirst("Ã©", "é");
			text = text.replaceFirst("Ã‰", "É");
		}
		return text;
	}
	
}
