package Testes;

import static org.junit.Assert.*;

import org.junit.Test;

import BDA.EmailAPI;
import junit.framework.Assert;

public class TestEmail extends EmailAPI{

	@Test
	public void test() {
		EmailAPI P = new EmailAPI();
		P.iniSessao("joao@gmail.com", "pass");
		assertEquals("pass",P.getPass());
	}
	public void test1() {
		EmailAPI P1 = new EmailAPI();
		P1.iniSessao("manuel@gmail.com", "12345");
		assertEquals("manuel@gmail.com",P1.getMail());
	}
}
