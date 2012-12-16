
import java.io.*;
import java.util.Scanner;
import ahoCorasick.AhoCorasickSearch;

public class ACHaku {

    public static void main(String[] args) throws FileNotFoundException {
        AhoCorasickSearch hakukone = new AhoCorasickSearch();
        if (args.length == 0) {
            System.out.println("Ei parametreja");
            return;
        }
        for (int i = 1; i < args.length; ++i) {
            hakukone.lisaaHakuSana(args[i]);
            System.out.println("param " + i + ": " + args[i]);
        }
        hakukone.tulostaTiedot();
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
                System.out.println("Löytynyt " + osumat);
            }
        }
        System.out.println("Merkkejä yhteensä " + hakukone.annaMerkkienMaara()
                + " kpl.");
        tiedosto.close();
    }
}
