/*
 * JonoLista - luokka
 * Linkitetty jonorakenne, eli vanhin tulee aina ulos ensimmäisenä
 */

package ahoCorasick;

public class JonoLista {
	private JonoListanSolmu jononAlku;
	private JonoListanSolmu jononLoppu;

	/*
	 * Konstruktori
	 */
	public JonoLista() {
		jononAlku = null;
		jononLoppu = null;
	}

	/*
	 * Lisää jonoon solmun(TrieSolmu)
	 */
	public void lisaaSolmu(TrieSolmu lisattava) {
		if (this.jononLoppu == null) {
			this.jononLoppu = new JonoListanSolmu(lisattava);
			this.jononAlku = this.jononLoppu;
			return; // lisätty tyhjään jonoon, joten lopetetaan
		}
		JonoListanSolmu uusi = new JonoListanSolmu(lisattava);
		this.jononLoppu.asetaSeuraavaSolmu(uusi);
		this.jononLoppu = uusi;
	}

	/*
	 * Palauttaa boolean-tiedon onko jono tyhjä
	 */
	public boolean onkoTyhja() {
		if (this.jononAlku == null)
			return true;
		else
			return false;
	}

	/*
	 * Poistaa jonosta vuorossa olevan Palauttaa poistetun solmun
	 */
	public TrieSolmu poistaSolmu() {
		if (jononAlku == null)
			return null;

		JonoListanSolmu apu = this.jononAlku;
		if (this.jononAlku == this.jononLoppu) {
			this.jononAlku = null;
			this.jononLoppu = null;
		} else {
			this.jononAlku = this.jononAlku.annaSeuraavaSolmu();
		}
		return apu.annaSolmunTila();
	}
}
