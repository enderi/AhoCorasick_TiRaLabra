/*
 * JonoListanSolmu - luokka
 * Jonolista-luokan käyttämät solmut.
 * Solmut pystyy taltioimaan arvoinaan vain TriePuun solmuja
 */

package ahoCorasick;

public class JonoListanSolmu {
	private TrieSolmu solmu;
	private JonoListanSolmu seuraava;

	/*
	 * Konstruktori Parametrina annetaan ensimmäinen solmu, josta muodostetaan
	 * jonoon sopiva solmu
	 */
	public JonoListanSolmu(TrieSolmu solmunen) {
		this.solmu = solmunen;
		this.seuraava = null;
	}

	/*
	 * Konstruktori Parametreina annetaan ensimmäinen solmu
	 * 
	 * public JonoListanSolmu(TrieSolmu solmunen,JonoListanSolmu ed){
	 * this.solmu=solmunen; }
	 */

	/*
	 * Palauttaa solmua seuraavan solmun
	 */
	public JonoListanSolmu annaSeuraavaSolmu() {
		return this.seuraava;
	}

	/*
	 * Palauttaa solmua vastaavan TrieSolmun
	 */
	public TrieSolmu annaSolmunTila() {
		return this.solmu;
	}

	/*
	 * Yhdistää solmun seuraavaan solmuun
	 */
	public void asetaSeuraavaSolmu(JonoListanSolmu seur) {
		this.seuraava = seur;
	}
}
