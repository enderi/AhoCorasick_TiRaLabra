package testAhoCorasick;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import ahoCorasick.AhoCorasickSearch;

public class AhoCorasickSearchTest extends TestCase {
	private AhoCorasickSearch hakukone;

	@Before
	public void setUp() throws Exception {
		hakukone = new AhoCorasickSearch();
	}

	@Test
	public void testLoytaakoKoneAnnetutEsiintymat() {

		hakukone.lisaaHakuSana("testisana");
		assertEquals(hakukone.etsiMerkkijonosta("tama on testisananen"),
				"{17: testisana}");
		hakukone.lisaaHakuSana("sana");
		hakukone.lisaaHakuSana("testi");
		assertEquals(hakukone.etsiMerkkijonosta("tama on testisananen"),
				"{13: testi, 17: testisana, 17: sana}");
		hakukone.lisaaHakuSana("teste");
		hakukone.lisaaHakuSana("testa");
		hakukone.lisaaHakuSana("esti");
		hakukone.tulostaTiedot();
		assertNull(hakukone.etsiMerkkijonosta(""));
		assertEquals(hakukone.etsiMerkkijonosta("testataan testillä"),
				"{5: testa, 15: testi, 15: esti}");
	}
}
