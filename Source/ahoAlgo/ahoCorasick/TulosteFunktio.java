/*
 * TulosteFunktio
 * Rajapintaluokka ALTulosteFunktiolle
 */

package ahoCorasick;

public interface TulosteFunktio {
	public String[] annaSanaIndeksille(int indeksi);

	public void lisaaSana(int indeksi, String sana);

	public void lisaaSanat(int indeksi, String[] sanat);

	public void tulosta();
}
