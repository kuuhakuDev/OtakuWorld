package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algoritmos.Busqueda;
import junit.framework.Assert;
import values.ValuesStrings;

public class BusquedaTest {
	
	String inicio = ValuesStrings.INICIO_MANGA;
	String fin = ValuesStrings.FIN_MANGA;
	String titulo = ValuesStrings.TITULO_MANGA;
	String url = ValuesStrings.URL_MANGA;
	String detalles = ValuesStrings.DETALLES_MANGA;
	String portada = ValuesStrings.PORTADA_MANGA;
	String puntuacion = ValuesStrings.PUNTUACION_MANGA;
	Busqueda b;

	@Before
	public void Clases() {
		b = new Busqueda(inicio, fin, titulo, url, detalles, portada, puntuacion);
		b.actualizarCodigo("http://leomanga.com/directorio-manga");
	}
	
	@Test
	public void testBuscarLinea() {
		String actual = b.buscarLinea(url, inicio, fin, 0);
		String expected = "<li class=\"manga-all col-xs-12 col-md-6\" id=\"boku-no-hero-academia\"><a href=\"/manga/boku-no-hero-academia\">";
		String message = "..." + actual + "...\n";
		
		Assert.assertEquals(message, expected, actual);
	}
	
	@Test
	public void testComillas() {
		String param = "<li class=\"manga-all col-xs-12 col-md-6\" id=\"one-piece\"><a href=\"/manga/one-piece\">";
		String actual = b.comillas(param, "href", 0);
		String expected = "/manga/one-piece";
		
		Assert.assertEquals("Valor = " + actual + "\n", expected, actual);
	}
	
	@Test
	public void testEtiquetas() {
		String param = "<span class=\"label label-info pull-right nota-manga\">9.7</span>";
		String actual = b.etiquetas(param, "class=\"label label-info pull-right nota-manga\"", 0);
		String expected = "9.7";
		String message = "..." + actual + "...\n";
		
		Assert.assertEquals(message, expected, actual);
	}
	
	@Test
	public void testTitulo() {
		String actual = b.getTitulo(0);
		String expected = "Boku no Hero Academia";
		String message = "..." + actual + "...\n";
		Assert.assertEquals(message, expected, actual);
	}
	
	@Test
	public void testURL() {
		String actual = b.getURL(0);
		String expected = "/manga/boku-no-hero-academia";
		String message = "..." + actual + "...\n";
		Assert.assertEquals(message, expected, actual);
	}
	
	@Test
	public void testDetalles() {
		String actual = b.getDetalles(0);
		String expected = "La historia se sitúa en una sociedad actual, lo único que la distingue es el hecho de que las personas con superpoderes se han vuelto algo común en todo el mund...";
		String message = "..." + actual + "...\n";
		Assert.assertEquals(message, expected, actual);
	}

	@Test
	public void testPuntuacion() {
		String actual = b.getPuntuacion(0);
		String expected = "9.4";
		String message = "..." + actual + "...\n";
		Assert.assertEquals(message, expected, actual);
	}
}





 	