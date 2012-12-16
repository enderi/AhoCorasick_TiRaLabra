/*
 * KaariJoukko - luokka
 * Pitää yllä TriePuun solmuihin liittyvää kaarien joukkoa
 */

package ahoCorasick;

public class KaariJoukko {
	protected final int OLETUSALKIOLKM = 2;
	protected Kaari[] kaaret;
	protected int lastenLkm;

	/*
	 * Konstruktori
	 */
	public KaariJoukko() {
		this.kaaret = new Kaari[this.OLETUSALKIOLKM];
		this.lastenLkm = 0;
	}

	/*
	 * Palauttaa kaarijoukon taulukkona
	 */
	public Kaari[] annaKaaret() {
		if (lastenLkm == 0)
			return null;
		else {
			Kaari[] apu = new Kaari[this.lastenLkm];
			for (int i = 0; i < this.lastenLkm; ++i) {
				apu[i] = this.kaaret[i];
			}
			return apu;
		}
	}

	/*
	 * Etsii kaarien joukosta kirjaimelle vastaavaa kaarta Palauttaa kaaren jos
	 * sellainen löytyy, muuten null
	 */
	public Kaari etsiKirjaintaKaarista(char kirjain) {

		int alku = 0;
		int loppu = this.lastenLkm - 1;
		int keskiKohta = (loppu + alku) / 2;
		while (alku <= loppu) {
			if (this.kaaret[keskiKohta].annaKaarenKirjain() == kirjain)
				return this.kaaret[keskiKohta];
			if (this.kaaret[keskiKohta].annaKaarenKirjain() > kirjain) {
				loppu = keskiKohta - 1;
				keskiKohta = (loppu + alku) / 2;
			} else if (this.kaaret[keskiKohta].annaKaarenKirjain() < kirjain) {
				alku = keskiKohta + 1;
				keskiKohta = (loppu + alku) / 2;
			}
		}
		return null;
	}

	/*
	 * Lisää kaaren parametrina annetulle kirjaimelle Palauttaa Solmun johon
	 * kaaren päähän luodaan TriePuu-viitettä tarvitaan, jotta tilalaskuria
	 * voidaan käyttää
	 */
	public TrieSolmu lisaaJoukkoonKaari(char kirjain, TriePuu kutsuja,
			int tasoluku) {
		Kaari apu = this.etsiKirjaintaKaarista(kirjain);

		if (apu == null) {
			// jos taulukko taynna, tuplataan sen koko
			if (this.lastenLkm == this.kaaret.length)
				tuplaaTaulukko();
			apu = new Kaari(kirjain, tasoluku + 1, kutsuja
					.kasvataJaAnnaTilaluku());
			// lisätään alkio ja jarjestetaan joukko
			this.kaaret[this.lastenLkm] = apu;
			++this.lastenLkm;
			if (this.lastenLkm > 1) {
				int i = this.lastenLkm - 1;
				while (i > 0) {
					if (this.kaaret[i - 1].annaKaarenKirjain() > this.kaaret[i]
							.annaKaarenKirjain()) {
						Kaari apuKaari = new Kaari(this.kaaret[i - 1]);
						this.kaaret[i - 1] = this.kaaret[i];
						this.kaaret[i] = apuKaari;
					}
					--i;
				}

			}
		}
		return apu.annaKohdeSolmu();
	}

	/*
	 * Kaksinkertaistaa kaaritaulukon koon
	 */
	private void tuplaaTaulukko() {
		Kaari[] apu = new Kaari[this.kaaret.length * 2];
		for (int i = 0; i < this.kaaret.length; ++i) {
			apu[i] = this.kaaret[i];
		}
		this.kaaret = apu;
	}
}
