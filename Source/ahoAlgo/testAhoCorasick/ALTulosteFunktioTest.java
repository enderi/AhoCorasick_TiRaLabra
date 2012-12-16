package testAhoCorasick;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ahoCorasick.ALTulosteFunktio;
import ahoCorasick.TulosteFunktio;

public class ALTulosteFunktioTest extends TestCase {
	private TulosteFunktio tulostefunkkis;

	@Before
	public void setUp() throws Exception {
		this.tulostefunkkis = new ALTulosteFunktio();

	}

	@Test
	public void testToimiikoTulosteFunktioOikein() {
		this.tulostefunkkis.tulosta();
		this.tulostefunkkis.lisaaSana(0, "sana");
		this.tulostefunkkis.tulosta();
		this.tulostefunkkis.lisaaSana(2, "sana");
		this.tulostefunkkis.lisaaSana(0, "sana");
		this.tulostefunkkis.tulosta();
		String[] sanat = { "testi", "sana", "olio" };
		this.tulostefunkkis.lisaaSanat(2, sanat);
		this.tulostefunkkis.tulosta();

	}

}
