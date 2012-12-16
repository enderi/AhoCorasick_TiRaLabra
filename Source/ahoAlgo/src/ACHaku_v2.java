
import java.io.*;
import java.util.Scanner;
import ahoCorasick.AhoCorasickSearch;

public class ACHaku_v2 {
	// param1: tiedosto josta etsitään
	// param2: tiedosto jossa hakusanat
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("Väärä määrä parametreja");
            return;
        }

	AhoCorasickSearch hakukone = new AhoCorasickSearch();

	File hakusanaTiedosto = new File(args[1]);
	if(!hakusanaTiedosto.exists()){
		System.out.println("Hakusanatiedoston avaaminen epäonnistui");
		return;
	}

        Scanner hakusanaTied = new Scanner(hakusanaTiedosto);
        System.out.println("Luetaan hakusanat tiedostosta " + args[1]);
        while (hakusanaTied.hasNextLine()) {
            String hakusana = hakusanaTied.nextLine();
            hakukone.lisaaHakuSana(hakusana);
	    System.out.println("Lisätty: "+hakusana);
        }
	hakusanaTied.close();

        //hakukone.tulostaTiedot();
        if (args.length < 1) {
            return; // ei hakusanoja
        }
        File tiedostoKahva = new File(args[0]);

        if (!tiedostoKahva.exists()) {
            System.out.println("Ei loydy tiedostoa");
            return;
        }
        Scanner tiedosto = new Scanner(tiedostoKahva);
        System.out.println("Etsitään tiedostosta " + args[0]);
        while (tiedosto.hasNextLine()) {
            String rivi = tiedosto.nextLine();
            String osumat = hakukone.etsiMerkkijonosta(rivi);
            if (osumat != null) {
                System.out.println("Löytynyt " + osumat+
				    ", riviltä:\n \""+
				    rivi+"\"\n");
            }
        }
        System.out.println("Merkkejä yhteensä " + hakukone.annaMerkkienMaara()
                + " kpl.");
        tiedosto.close();
    }
}

