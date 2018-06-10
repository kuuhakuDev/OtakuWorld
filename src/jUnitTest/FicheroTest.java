package jUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import recursos.Fichero;

public class FicheroTest {

	@Test
	public void test() {
		String actual =Fichero.extraerNombre("/uploads/images/mangas/boku-no-hero-academia/manga-boku-no-hero-academia.jpg");
		String expected = "manga-boku-no-hero-academia.jpg";
		String message = "..." +actual+"...\n";
		Assert.assertEquals(message, expected, actual);
	}
	
	@Test
	public void testExiste() {
		boolean actual = Fichero.existe("cache");
		boolean expected = false;
		Assert.assertEquals(expected, actual);
	}

}
