/*
 * TrieSolmu - luokka
 * TriePuu-luokka käyttää puun solmuina tätä luokkaa
 */

package ahoCorasick;

public class TrieSolmu {
	protected int tilaNumero;

	protected final int OLETUSALKIOLKM = 2;
	protected int lastenLkm;
	protected int tasoluku; // korkeus puussa

	protected Kaari[] lapset;
	protected KaariJoukko kaaret;

	/*
	 * Konstruktori
	 */
	public TrieSolmu(int tasoNro) {
		lapset = new Kaari[this.OLETUSALKIOLKM];
		this.lastenLkm = 0;
		this.tasoluku = tasoNro;
		this.kaaret = new KaariJoukko();
	}

	/*
	 * Konstruktori Parametreina annetaan solmun tasonumero ja tilanumero
	 */
	public TrieSolmu(int tasoNro, int tilaNro) {
		this.tilaNumero = tilaNro;
		lapset = new Kaari[this.OLETUSALKIOLKM];
		this.lastenLkm = 0;
		this.tasoluku = tasoNro;
		this.kaaret = new KaariJoukko();
	}

	/*
	 * Palauttaa solmun kaaret taulukkona
	 */
	public Kaari[] annaSolmunKaaret() {
		return this.kaaret.annaKaaret();
	}

	/*
	 * Palauttaa solmun tasoluvun
	 */
	public int annaSolmunTasoNro() {
		return this.tasoluku;
	}

	/*
	 * Palauttaa solmun tilanumeron
	 */
	public int annaSolmunTila() {
		return this.tilaNumero;
	}

	/*
	 * Asettaa solmulle tilanumeron
	 */
	public void asetaSolmunTilaNumero(int numero) {
		this.tilaNumero = numero;
	}

	/*
	 * Etsii solmuun liitetystä kaarijoukosta parametrina annetulle kirjaimelle
	 * sopivaa kaarta ja palauttaa kaaren päässä olevan solmun
	 */
	public TrieSolmu etsiKirjainSolmunKaarista(char kirjain) {
		Kaari apu = this.kaaret.etsiKirjaintaKaarista(kirjain);
		if (apu == null)
			return null;
		return apu.annaKohdeSolmu();
	}

	/*
	 * Lisää kaarijoukkoon parametrina annetulle kirjaimelle kaaren
	 * TriePuu-viite annetaan parametrina jotta kutsuttava metodi voisi
	 * kasvattaa puun tilalaskuria Palauttaa kaaren päähän luotavan TrieSolmun
	 */
	public TrieSolmu lisaaKirjainSolmunKaareksi(char kirjain, TriePuu kutsuja) {
		TrieSolmu apu = kaaret.lisaaJoukkoonKaari(kirjain, kutsuja,
				this.tasoluku + 1);
		return apu;
	}

	/*
	 * toString
	 */
	public String toString() {
		return "(" + this.tilaNumero + ")";
	}

	/*
	 * Tulostaa solmun lapset
	 */
	public void tulostaSolmunLapset() {
		for (int i = 0; i < this.lastenLkm; ++i) {
			System.out.println(this.lapset[i].annaKaarenKirjain());
		}
	}
}
