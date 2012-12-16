/*
 * DynaaminenTaulukko - luokka
 * Tarpeen vaatiessa kasvava taulukkototeutus 
 * geneeriselle tietotyypille
 */

package ahoCorasick;

public class DynaaminenTaulukko<O> {
	private final int OLETUSKOKO = 5;
	private O[] taulukko;
	private int alkioidenLkm;

	/*
	 * Konstruktori
	 */
	public DynaaminenTaulukko() {
		this.taulukko = (O[]) new Object[this.OLETUSKOKO];
		this.alkioidenLkm = 0;
	}

	/*
	 * Palauttaa arvon pyydetystä indeksistä
	 */
	public O annaArvo(int indeksi) {
		if (indeksi < 0 || indeksi >= this.alkioidenLkm)
			return null;
		return this.taulukko[indeksi];
	}

	/*
	 * Palauttaa kopion alkoit sisältävästä taulukosta
	 */
	public O[] annaArvotTaulukkona() {
		if (this.alkioidenLkm == 0)
			return null;
		O[] palaute = (O[]) new Object[this.alkioidenLkm];
		System.out.println("Annetaan taulukko kooltaan " + palaute.length);
		for (int i = 0; i < this.alkioidenLkm; ++i)
			palaute[i] = this.taulukko[i];
		return palaute;
	}

	/*
	 * Palauttaa alkioiden määrän taulukossa
	 */
	public int annaKoko() {
		return this.alkioidenLkm;
	}

	/*
	 * Lisää taulukkoon uuden arvon uuteen indeksiin Palauttaa true jos lisäys
	 * onnistui ja false jos ei
	 */
	public boolean lisaa(O alkio) {
		if (this.alkioidenLkm == this.taulukko.length)
			tuplaaTaulukonKoko();
		this.taulukko[this.alkioidenLkm] = alkio;
		++this.alkioidenLkm;
		return true;
	}

	/*
	 * Lisää arvon annettuun indeksiin Palauttaa true jos lisäys onnistui, false
	 * jos ei
	 */
	public boolean lisaa(O alkio, int indeksi) {
		if (indeksi < 0 || indeksi >= this.alkioidenLkm)
			return false;
		this.taulukko[indeksi] = alkio;
		return true;
	}

	/*
	 * Kopioi alkiot parametrina annettuun merkkijonotaulukkoon Arvot castataan
	 * String:ksi
	 */
	public boolean muutaTaulukoksi(String[] taulu) {
		if (taulu.length != this.alkioidenLkm)
			return false;
		for (int i = 0; i < this.alkioidenLkm; ++i)
			taulu[i] = (String) this.taulukko[i];
		return true;
	}

	/*
	 * toString
	 */
	public String toString() {
		if (this.alkioidenLkm == 0)
			return null;
		String teksti = "{";
		for (int i = 0; i < this.alkioidenLkm; ++i)
			if (i + 1 < this.alkioidenLkm)
				teksti += this.taulukko[i] + ", ";
			else
				teksti += this.taulukko[i];
		teksti += "}";
		return teksti;
	}

	/*
	 * Tuplaa nykyisen taulukon koon
	 */
	private void tuplaaTaulukonKoko() {
		O[] apu = (O[]) new Object[this.alkioidenLkm * 2];
		for (int i = 0; i < this.alkioidenLkm; ++i) {
			apu[i] = this.taulukko[i];
		}
		this.taulukko = apu;
	}
}
