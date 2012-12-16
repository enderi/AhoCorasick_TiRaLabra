package testAhoCorasick;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ahoCorasick.TriePuu;
import ahoCorasick.TrieSolmu;

public class TriePuuTest extends TestCase {
	TriePuu testipuu;

	@Before
	public void setUp() {
		this.testipuu = new TriePuu();
	}

	@Test
	public void testJuuriSolmunTilaOnNolla() {
		assertEquals(this.testipuu.annaJuuri().annaSolmunTila(), 0);
	}

	@Test
	public void testJuuriSolmustaLiikutaanOikeinKirjaimienAvulla() {
		this.testipuu = new TriePuu();
		this.testipuu.lisaaSanaPuuhun("testi");

		TrieSolmu apu = this.testipuu.annaJuuri();
		apu = apu.etsiKirjainSolmunKaarista('t');
		assertEquals(apu.annaSolmunTila(), 1);

		apu = apu.etsiKirjainSolmunKaarista('e');
		assertEquals(apu.annaSolmunTila(), 2);
		apu = apu.etsiKirjainSolmunKaarista('s');
		assertEquals(apu.annaSolmunTila(), 3);
		apu = apu.etsiKirjainSolmunKaarista('t');
		assertEquals(apu.annaSolmunTila(), 4);
		apu = apu.etsiKirjainSolmunKaarista('i');
		assertEquals(apu.annaSolmunTila(), 5);
		apu = apu.etsiKirjainSolmunKaarista('t');
		assertNull(apu);
		// lisätään sana puuhun

		this.testipuu.lisaaSanaPuuhun("tesla");
		apu = this.testipuu.annaJuuri();
		apu = apu.etsiKirjainSolmunKaarista('t');
		assertEquals(apu.annaSolmunTila(), 1);
		apu = apu.etsiKirjainSolmunKaarista('e');
		assertEquals(apu.annaSolmunTila(), 2);
		apu = apu.etsiKirjainSolmunKaarista('s');
		assertEquals(apu.annaSolmunTila(), 3);
		apu = apu.etsiKirjainSolmunKaarista('l');
		assertEquals(apu.annaSolmunTila(), 6);
		apu = apu.etsiKirjainSolmunKaarista('a');
		assertEquals(apu.annaSolmunTila(), 7);
		apu = apu.etsiKirjainSolmunKaarista('t');
		assertNull(apu);
	}

	@Test
	public void testTilaMaaraKasvaaSanojaLisattaessa() {
		assertEquals(this.testipuu.annaTilojenLkm(), 0);
		this.testipuu.lisaaSanaPuuhun("testisana");
		assertEquals(this.testipuu.annaTilojenLkm(), 9);
	}
}
