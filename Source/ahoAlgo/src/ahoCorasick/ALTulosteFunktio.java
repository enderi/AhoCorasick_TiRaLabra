/*
 * ALTulosteFunktio - luokka
 * Tiloihin(eli solmuihin) liittyvien tulosteiden
 * taltioimiseen ja hakemiseen.
 * Käytetään rajapintaluokan "TulosteFunktio" kautta
 */

package ahoCorasick;

public class ALTulosteFunktio implements TulosteFunktio {

	// taulukollinen taulukoita tilojen tulosteille
	private DynaaminenTaulukko<String>[] tilojenTulosteet;

	/*
	 * Konstruktori
	 */
	public ALTulosteFunktio() {
		this.tilojenTulosteet = null;
	}

	/*
	 * annaSanaIndeksille(int) Palauttaa pyydetyssä indeksissä olevat sanat
	 * taulukkona
	 */
	public String[] annaSanaIndeksille(int indeksi) {
		DynaaminenTaulukko<String> apu = this.tilojenTulosteet[indeksi];
		if (apu != null) {
			String[] palaute = new String[apu.annaKoko()];
			apu.muutaTaulukoksi(palaute);
			return palaute;
		} else
			return null;
	}

	/*
	 * kasvataTaulukko(int) kasvattaa taulukon kokoa haluttuun kokoon asti
	 */
	private void kasvataTaulukkoa(int uusikoko) {
		DynaaminenTaulukko<String>[] apu = (DynaaminenTaulukko<String>[]) new DynaaminenTaulukko[uusikoko];
		for (int i = 0; i < this.tilojenTulosteet.length; ++i) {
			apu[i] = this.tilojenTulosteet[i];
		}
		this.tilojenTulosteet = apu;
	}

	/*
	 * lisaaSana(int, String) Lisää sanan annettussa indeksissä olevaan
	 * DTaulukkoon Jos taulukon koko ei yletä indeksiin asti, kasvatetaan
	 * taulukkoa
	 */
	public void lisaaSana(int indeksi, String sana) {
		if (this.tilojenTulosteet == null) {
			this.tilojenTulosteet = (DynaaminenTaulukko<String>[]) new DynaaminenTaulukko[indeksi + 1];
		} else if (this.tilojenTulosteet.length < indeksi + 1) {
			this.kasvataTaulukkoa(indeksi + 1);
		}

		DynaaminenTaulukko<String> apu = this.tilojenTulosteet[indeksi];
		if (apu == null) {
			apu = new DynaaminenTaulukko<String>();
			this.tilojenTulosteet[indeksi] = apu;
		}
		apu.lisaa(sana);
	}

	/*
	 * lisaaSanat(int, String[]) Lisää sanat annettuun indeksiin Jos taulukon
	 * koko ei yletä indeksiin asti, kasvatetaan taulukkoa
	 */
	public void lisaaSanat(int indeksi, String[] sanat) {
		if (sanat == null)
			return;
		for (int i = 0; i < sanat.length; ++i) {
			this.lisaaSana(indeksi, sanat[i]);
		}
		System.out.println();
	}

	/*
	 * tulosta() Tulostaa taulukkoon liittyvät DynaaminenTaulukot
	 */
	public void tulosta() {
		if (this.tilojenTulosteet == null)
			return;
		for (int i = 0; i < this.tilojenTulosteet.length; ++i) {
			System.out.print("Output(" + i + "): ");
			if (this.tilojenTulosteet[i] != null) {
				for (int j = 0; j < this.tilojenTulosteet[i].annaKoko(); ++j)
					System.out
							.print(this.tilojenTulosteet[i].annaArvo(j) + " ");
			}
			System.out.println();
		}
	}
}