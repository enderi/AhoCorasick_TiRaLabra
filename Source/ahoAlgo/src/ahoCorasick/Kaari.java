/*
 * Kaari - luokka
 * TriePuun solmujen välisien kaarien toteutus
 */

package ahoCorasick;

public class Kaari {
	protected char kaarenKirjain;
	protected TrieSolmu kohdeSolmu;
	protected int lastentasoluku; // lasten korkeus puussa

	/*
	 * Konstruktori Parametreina annetaan kaareen liitettävä kirjain, luotavan
	 * lapsen tasonumero TriePuussa ja tilanumero
	 */
	public Kaari(char kirjain, int taso, int tilanumero) {
		this.kaarenKirjain = kirjain;
		this.lastentasoluku = taso;
		this.kohdeSolmu = new TrieSolmu(this.lastentasoluku);
		this.kohdeSolmu.asetaSolmunTilaNumero(tilanumero);
	}

	/*
	 * Konstruktori Parametrina annetaan kaari jonka tiedot kopioidaan
	 */
	public Kaari(Kaari kopioitava) {
		this.kaarenKirjain = kopioitava.annaKaarenKirjain();
		this.kohdeSolmu = kopioitava.annaKohdeSolmu();
		this.lastentasoluku = kopioitava.annaLastenTasoLuku();
	}

	/*
	 * Palauttaa kaareen liittyvän kirjaimen
	 */
	public char annaKaarenKirjain() {
		return this.kaarenKirjain;
	}

	/*
	 * Palauttaa kaaren päättymissolmun
	 */
	public TrieSolmu annaKohdeSolmu() {
		return this.kohdeSolmu;
	}

	/*
	 * Palauttaa kaareen loppupäässä olevien solmujen tasonumeron
	 */
	public int annaLastenTasoLuku() {
		return this.lastentasoluku;
	}

	/*
	 * Asettaa kaaren loppumissolmun
	 */
	public void asetaKaarenKohde(TrieSolmu kohde) {
		this.kohdeSolmu = kohde;
	}
}
