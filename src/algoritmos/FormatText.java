package algoritmos;

public class FormatText {

	public static String utf8(String text) {
		
		while(text.indexOf("�") != -1) {
			text = text.replaceFirst("ñ", "�");
			text = text.replaceFirst("ó", "�");
			text = text.replaceFirst("ú", "�");
			text = text.replaceFirst("í", "�");
			text = text.replaceFirst("á", "�");
			text = text.replaceFirst("é", "�");
			text = text.replaceFirst("É", "�");
		}
		return text;
	}
	
}
