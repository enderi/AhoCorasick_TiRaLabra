package testAhoCorasick;

import junit.framework.TestCase;
import ahoCorasick.DynaaminenTaulukko;

public class DynaaminenTaulukkoTestit extends TestCase {
	public void testDynaamisenTaulukonTestaus() {
		DynaaminenTaulukko<String> testiTaulu = new DynaaminenTaulukko<String>();
		assertTrue(testiTaulu.annaKoko() == 0);
		assertTrue(testiTaulu.lisaa("teksti"));
		assertEquals(testiTaulu.annaArvo(0), "teksti");
		assertTrue(testiTaulu.annaKoko() == 1);
		assertFalse(testiTaulu.lisaa("teksti2", -2));
	}
}
