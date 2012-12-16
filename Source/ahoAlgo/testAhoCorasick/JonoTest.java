package testAhoCorasick;

import junit.framework.TestCase;
import ahoCorasick.JonoLista;
import ahoCorasick.TrieSolmu;

public class JonoTest extends TestCase {

	public void testJononToimintaJaTyhjentyminen() {
		TrieSolmu apu1 = new TrieSolmu(1);
		TrieSolmu apu2 = new TrieSolmu(2);
		TrieSolmu apu3 = new TrieSolmu(3);
		JonoLista jono = new JonoLista();
		assertTrue(jono.onkoTyhja());

		jono.lisaaSolmu(apu1);
		assertTrue(!jono.onkoTyhja());

		TrieSolmu poisto = jono.poistaSolmu();
		assertTrue(poisto == apu1);
		assertTrue(jono.onkoTyhja());

		jono.lisaaSolmu(apu1);
		jono.lisaaSolmu(apu2);
		jono.lisaaSolmu(apu3);
		assertTrue(jono.poistaSolmu() == apu1);
		assertTrue(jono.poistaSolmu() == apu2);
		assertTrue(jono.poistaSolmu() == apu3);
		assertTrue(jono.onkoTyhja());
	}

}
