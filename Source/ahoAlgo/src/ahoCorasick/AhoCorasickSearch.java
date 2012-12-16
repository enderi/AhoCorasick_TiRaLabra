/*
 * AhoCorasicSearch - luokka
 * Paketin varsinainen pääluokka, jonka avulla haut tehdään
 */

package ahoCorasick;

public class AhoCorasickSearch {

	// Solmujen korkeuteen puussa viittaava vakio
	private static final int JUURENTASONRO = 0;

	// Trie-puu johon hakusanat tallennetaan
	private TriePuu hakuSanaPuu;

	private DynaaminenTaulukko<String> hakusanat;
	// tiloihin liittyvät tulosteet
	private TulosteFunktio tilojenTulosteet;

	// koneen tämänhetkinen tila
	private TrieSolmu nykyinenTila;

	// kertoo voidaanko etsintää alkaa suorittamaan
	private boolean koneAloitusKunnossa;

	// jos tilasta ei lähde kaarta annetulla kirjaimella
	// niin failuresiirtymä kertoo mihin siirrytään
	private TrieSolmu[] failureSiirtymat;

	// laskuri joka kertoo kuinka monta merkkiä on luettu(totaali)
	private int luetutMerkit;

	// hakua koskevia asetuksia(oletuksen epätosi)
	private boolean erotteleIsotJaPienet = false;

	/*
	 * Konstruktori
	 */
	public AhoCorasickSearch() {
		this.hakuSanaPuu = new TriePuu();
		this.tilojenTulosteet = new ALTulosteFunktio();
		this.hakusanat = new DynaaminenTaulukko<String>();
		this.nykyinenTila = null;
		this.failureSiirtymat = null;
		this.koneAloitusKunnossa = false;
		this.luetutMerkit = 0;
	}

	/*
	 * Konstruktori Parametrina kirjainkoon huomiointi
	 */
	public AhoCorasickSearch(boolean erotteleKirjainKoko) {
		this.hakuSanaPuu = new TriePuu();
		this.tilojenTulosteet = new ALTulosteFunktio();
		this.hakusanat = new DynaaminenTaulukko<String>();
		this.nykyinenTila = null;
		this.failureSiirtymat = null;
		this.koneAloitusKunnossa = false;
		this.luetutMerkit = 0;
		this.erotteleIsotJaPienet = erotteleKirjainKoko;
	}

	/*
	 * annaMerkkienMaara Palauttaa käsiteltyjen merkkien lukumäärän
	 */
	public int annaMerkkienMaara() {
		return this.luetutMerkit;
	}

	/*
	 * annaTilaSiirtymaKirjaimelle(TrieSolmu, char) Toteuttaa tilasiirtymät
	 * Palauttaa TrieSolmuna uuden tilan Huomioitavaa on, että puun tasolla 1(ja
	 * luonnollisesti 0), paluuarvoksi voi tulla nykyinen tila, jos ei
	 * kirjaimelle löydy syvemmälle vievää kaarta. Tämä koskee ainoastaan
	 * tilannetta jolloin on kaksi samaa kirjainta peräkkäin.
	 */
	private TrieSolmu annaTilaSiirtymaKirjaimelle(TrieSolmu tila, char kirjain) {
		TrieSolmu kohde = tila.etsiKirjainSolmunKaarista(kirjain);
		if (kohde == null && tila.annaSolmunTasoNro() <= AhoCorasickSearch.JUURENTASONRO)
			return tila;
		return kohde;
	}

	/*
	 * etsiMerkkijonosta(String) Saa parametrina merkkijonon, jonka käy läpi
	 * kirjain kerrallaan Palauttaa kaikki löytyneet hakusanat
	 */
	public String etsiMerkkijonosta(String rivi) {
		if (this.hakusanat.annaKoko() == 0) {
			return null;
		}

		DynaaminenTaulukko<String> osumat = new DynaaminenTaulukko<String>();
		if (!koneAloitusKunnossa)
			if (this.hakusanat.annaKoko() == 0)
				return "";
			else
				this.valmisteleHakuKuntoon();

		for (int i = 0; i < rivi.length(); ++i) {
			String[] tulos = this.kasitteleMerkki(rivi.charAt(i));
			if (tulos != null)
				for (int j = 0; j < tulos.length; ++j)
					osumat.lisaa(this.luetutMerkit + ": " + tulos[j]);
		}
		return osumat.toString();
	}

	/*
	 * kasitteleMerkki(char) Saa syötteenä yhden merkin, tekee tilasiirtymän
	 * Palauttaa uuteen tilaan liittyvät hakusanat sekä tiedon monesko merkki
	 * alusta oli
	 */
	private String[] kasitteleMerkki(char c) {
		if (!koneAloitusKunnossa)
			this.valmisteleHakuKuntoon();

		if (!this.erotteleIsotJaPienet)
			c = Character.toLowerCase(c);

		TrieSolmu uusiTila = this.annaTilaSiirtymaKirjaimelle(
				this.nykyinenTila, c);
		while (uusiTila == null) {
			this.nykyinenTila = this.failureSiirtymat[this.nykyinenTila
					.annaSolmunTila()];
			uusiTila = this.annaTilaSiirtymaKirjaimelle(this.nykyinenTila, c);
		}
		this.nykyinenTila = uusiTila;
		String[] tuloste = this.tilojenTulosteet
				.annaSanaIndeksille(this.nykyinenTila.annaSolmunTila());
		++this.luetutMerkit;
		return tuloste;
	}

	/*
	 * lisaaHakuSana(String) Lisää haettavien sanojen joukkoon hakusanan ja
	 * lisää sen myös puuhun
	 */
	public void lisaaHakuSana(String sana) {
		// Koska failurefunktiota muodostettaessa tulosteita yhdistellään,
		// on vaarana että jälkikäteen sanoja lisättäessä tulee tuplatulosteita
		// joten muodostamme puun tässä välissä uudestaan
		if (this.koneAloitusKunnossa) {
			this.tilojenTulosteet = new ALTulosteFunktio();
			this.hakuSanaPuu = new TriePuu();
			String[] hakusanaTaulukko = new String[this.hakusanat.annaKoko()];
			this.hakusanat.muutaTaulukoksi(hakusanaTaulukko);
			for (int i = 0; i < hakusanaTaulukko.length; ++i) {
				String lisattava;
				if (!this.erotteleIsotJaPienet)
					lisattava = hakusanaTaulukko[i].toLowerCase();
				else
					lisattava = hakusanaTaulukko[i];
				int lTila = this.hakuSanaPuu.lisaaSanaPuuhun(lisattava);
				this.tilojenTulosteet.lisaaSana(lTila, lisattava);
			}
			this.koneAloitusKunnossa = false;
		}
		// nyt lisäämme uuden sanan
		if (!this.erotteleIsotJaPienet) {
			sana = sana.toLowerCase();
		}

		int loppuTila = this.hakuSanaPuu.lisaaSanaPuuhun(sana);
		this.tilojenTulosteet.lisaaSana(loppuTila, sana);
		this.hakusanat.lisaa(sana);
	}

	/*
	 * tulostaTiedot Tulostaa tilat, niihin liittyvät siirtymät, tulostefunktion
	 * sekä hakusanat
	 */
	public void tulostaTiedot() {
		this.hakuSanaPuu.tulostaHakuPuu();
		if (this.hakuSanaPuu.annaTilojenLkm() > 0)
			this.valmisteleHakuKuntoon();
		this.tilojenTulosteet.tulosta();
		System.out.println("Hakusanat: " + this.hakusanat);
	}

	/*
	 * valmisteleHakuKuntoon() Ennen varsinaisen kohdetekstin läpikäyntiä täytyy
	 * rakentaa failure-funktio sekä nollata laskurit ja asettaa
	 * nykyinenTila-muuttuja osoittamaan juureen
	 */
	private void valmisteleHakuKuntoon() {
		TrieSolmu nollaTila = this.hakuSanaPuu.annaJuuri();
		
	// *** muodostetaan failurefunktio, 
	// *** eli taulukko minkä indeksi tarkoittaa tilaa
	// *** ja sen sisältämä alkio on failuresiirtymän kohdesolmu
		
		int tilojenLkm = this.hakuSanaPuu.annaTilojenLkm() + 1;
		this.failureSiirtymat = new TrieSolmu[tilojenLkm];
		
		JonoLista solmujono = new JonoLista();
				
		// ensin käsitellään juurisolmu
		TrieSolmu kasiteltava = nollaTila;
		Kaari[] apukaaret = kasiteltava.annaSolmunKaaret();

		// juuresta mennään juureen jos ei löydy sopivaa kaarta
		this.failureSiirtymat[0] = nollaTila;

		// ja sitten puun ensimmäisen tason (eli juurisolmun lapsien) käsittely
		// niidenkin failuresiirtymä osoittaa juureen
		for (int i = 0; i < apukaaret.length; ++i) {
			TrieSolmu kohde = apukaaret[i].annaKohdeSolmu();
			solmujono.lisaaSolmu(kohde);
			this.failureSiirtymat[kohde.annaSolmunTila()] = nollaTila;
		}

		// tämän jälkeen käsitellään kaikki jonossa olevat (ja sinne tulevat)
		// puu siis käydään leveyssuuntaisesti läpi
		while (!solmujono.onkoTyhja()) {
			TrieSolmu r = solmujono.poistaSolmu();
			apukaaret = r.annaSolmunKaaret();
			char a;
			if (apukaaret != null) {
				for (int i = 0; i < apukaaret.length; ++i) {
					a = apukaaret[i].annaKaarenKirjain();
					solmujono.lisaaSolmu(apukaaret[i].annaKohdeSolmu());
					
					TrieSolmu tila = this.failureSiirtymat[r.annaSolmunTila()];
					while (this.annaTilaSiirtymaKirjaimelle(tila, a) == null) {
						tila = this.failureSiirtymat[tila.annaSolmunTila()];
					}

					int kohdeSolmunTilaNro = apukaaret[i].annaKohdeSolmu()
							.annaSolmunTila();
					this.failureSiirtymat[kohdeSolmunTilaNro] = this
							.annaTilaSiirtymaKirjaimelle(tila, a);
					// haetaan failurefunktion päästä tulostefunktion arvo
					// lisättäväksi tilaan
					this.tilojenTulosteet.lisaaSanat(kohdeSolmunTilaNro,
							this.tilojenTulosteet.annaSanaIndeksille(this
									.annaTilaSiirtymaKirjaimelle(tila, a)
									.annaSolmunTila()));
				}
			}
		}
		
		// tilakoneen loput parametrit kuntoon
		this.nykyinenTila = this.hakuSanaPuu.annaJuuri();
		this.luetutMerkit = 0;
		this.koneAloitusKunnossa = true; // tekstin luku voi siis alkaa
	}
}
