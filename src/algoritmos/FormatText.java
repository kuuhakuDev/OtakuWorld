package algoritmos;

public class FormatText {

	public static String utf8(String text) {
		int i = 0;
		do {
			text = text.replaceFirst("Ã±", "ñ");//e'ne
			text = text.replaceFirst("Ã³", "ó");//o minuscula con acento
			text = text.replaceFirst("Ãº", "ú");//u minuscula con acento
			text = text.replaceFirst("Ã­", "í");//i minuscula con acento
			text = text.replaceFirst("Ã¡", "á");//a minuscula con acento
			text = text.replaceFirst("Ã©", "é");//e minuscula con acento
			text = text.replaceFirst("Ã�", "Á");//A mayuscula con acento
			text = text.replaceFirst("Ã‰", "É");//E mayuscula con acento
			text = text.replaceFirst("Â¡", "!");//Primer signo de admiracion 
			text = text.replaceFirst("Â¿", "¿");//Primer signo de interrogacion
			text = text.replaceFirst("Ã¼", "ü");//Dieresis
			text = text.replaceFirst("Ãš", "Ú");
			text = text.replaceFirst("Ã“", "Ó");
			text = text.replaceFirst("Ã²", "ò");
			text = text.replaceFirst("Ã¨", "è");
			text = text.replaceFirst("â€”", "–");
			text = text.replaceFirst("Âº", "º");
			text = text.replaceFirst("â€¢", "•");
			text = text.replaceFirst("â€¦", "…");
			text = text.replaceFirst("â€œ", "“");
			text = text.replaceFirst("â€", "”");
			text = text.replaceFirst("Â°", "°");
			text = text.replaceFirst("Â¥", "¥");
			i++;
			if(i > 100) {
				System.out.println(text);
			}
		}while(text.indexOf("Ã") != -1 || text.indexOf("â") != -1 || text.indexOf("Â") != -1);
		
		return text;
	}
	
}
