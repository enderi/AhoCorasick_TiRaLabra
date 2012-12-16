/*
 * TriePuu - luokka
 * Trierakenteen muodostamiseen
 */

package ahoCorasick;

public class TriePuu {
	protected int tilojenLkm;
	protected TrieSolmu juuri;
	protected TrieSolmu[] solmut;

	/*
	 * Konstruktori
	 */
	public TriePuu() {
		this.tilojenLkm = 0;
		this.juuri = new TrieSolmu(0, this.tilojenLkm);
		this.solmut = new TrieSolmu[this.tilojenLkm + 1];

	}

	/*
	 * Palauttaa puun juurisolmun TrieSolmuna
	 */
	public TrieSolmu annaJuuri() {
		return this.juuri;
	}

	/*
	 * Palauttaa puun solmujen eli tilojen lukumäärän
	 */
	public int annaTilojenLkm() {
		return this.tilojenLkm;
	}

	/*
	 * Kasvattaa tilojen lukumäärää yhdellä ja palauttaa saadun arvon
	 */
	public int kasvataJaAnnaTilaluku() {
		++this.tilojenLkm;
		return this.tilojenLkm;
	}

	/*
	 * Lisää puuhun parametrina annetun merkkijonon Palauttaa tilanumeron johon
	 * päädyttiin lisäyksen jälkeen
	 */
	public int lisaaSanaPuuhun(String sana) {
		if (sana.length() == 0)
			return 0;

		TrieSolmu aktiiviSolmu = this.juuri;

		for (int i = 0; i < sana.length(); ++i) {
			char apuKirjain = sana.charAt(i);
			aktiiviSolmu = aktiiviSolmu.lisaaKirjainSolmunKaareksi(apuKirjain,
					this);
		}
		return aktiiviSolmu.annaSolmunTila();
	}

	/*
	 * Tulostaa puun
	 */
	public void tulostaHakuPuu() {
		if (this.tilojenLkm == 0) {
			System.out.println("Puu on tyhjä");
			return;
		}
		TrieSolmu aloitus = this.juuri;
		System.out.println("Tulostetaan hakusana-puu, jossa "
				+ this.annaTilojenLkm() + "+1 tilaa ja juurella lapsia "
				+ this.annaJuuri().annaSolmunKaaret().length);
		tulostus_rekursio(aloitus, "");
	}

	/*
	 * Puun tulostustamiseen
	 */
	private void tulostus_rekursio(TrieSolmu aktiivi, String teksti) {
		Kaari[] kaaret = aktiivi.annaSolmunKaaret();
		if (kaaret == null) {
			System.out.println(teksti + aktiivi.annaSolmunTila());
			return;
		}
		for (int i = 0; i < kaaret.length; ++i) {
			tulostus_rekursio(kaaret[i].annaKohdeSolmu(), teksti
					+ aktiivi.annaSolmunTila() + " --"
					+ kaaret[i].annaKaarenKirjain() + "--> ");
		}
	}
}
