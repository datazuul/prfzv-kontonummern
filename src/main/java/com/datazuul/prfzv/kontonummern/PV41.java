package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 41
 *
 * <p>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 (modifiziert)</p>
 *
 * <p>
 * Die Berechnung erfolgt wie bei Verfahren 00</p>
 *
 * <p>
 * Ausnahme: Ist die 4. Stelle der Kontonummer (von links) = 9, so werden die Stellen 1 bis 3 nicht in die Prüfzifferberechnung einbezogen.</p>
 *
 * <pre>
 * Stellennr.:       1 2 3 4 5 6 7 8 9 10
 * Kontonr.:         4 0 1 9 1 1 0 0 0 8
 * Ktonr. umgesetzt: 0 0 0 9 1 1 0 0 0 8
 * Gewichtung:             1 2 1 2 1 2
 *
 *                         9+2+1+0+0+0 = 12
 *                         10 - 2 = 8
 *                         8 = Prüfziffer
 * </pre>
 *
 * <p>
 * Testkontonummern: 4013410024, 4016660195, 0166805317 4019310079, 4019340829, 4019151002</p>
 */
public class PV41 extends PRFZV {

  public PV41() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 1};

    // vorgegebener mModulo-Wert
    mModulo = 10;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 9;
  }

  @Override
  int getVergleichswert() {
    // mKontonummernummer zehnstellig machen
    while (mKontonummer.length() < 10) {
      mKontonummer = "0" + mKontonummer;
    }

    // Array mGewichtung füllen
    mGewichtung = new int[9];
    for (int i = mGewichtung.length - 1; i >= 0; i--) {
      mGewichtung[i] = mGewichtungswerte[mCounter];
      mCounter++;
      if (mCounter > mGewichtungswerte.length - 1) {
        mCounter = 0;
      }
    }
    Integer zwischenmErgebnis;
    String zwischenmErgebnisstring;
    int quersumme = 0;
    if (Integer.parseInt(mKontonummer.substring(3, 4)) == 9) {
      for (int i = 3; i < 9; i++) {
        System.out.println("9 gefunden");
        // Multiplikation der mGewichtung mit mKontonummer
        zwischenmErgebnis = (Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]);
        // Quersumme der MultiplikationsmErgebnisse bilden
        zwischenmErgebnisstring = zwischenmErgebnis.toString();
        if (zwischenmErgebnisstring.length() == 2) {
          quersumme = (Integer.parseInt(zwischenmErgebnisstring.substring(0, 1))
                  + Integer.parseInt(zwischenmErgebnisstring.substring(1)));
        } else {
          quersumme = zwischenmErgebnis;
        }
        // Quersummen addieren
        mErgebnis += quersumme;
      }
    } else {
      for (int i = 0; i < 9; i++) {
        // Multiplikation der mGewichtung mit mKontonummer
        zwischenmErgebnis = (Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]);
        // Quersumme der MultiplikationsmErgebnisse bilden
        zwischenmErgebnisstring = zwischenmErgebnis.toString();
        if (zwischenmErgebnisstring.length() == 2) {
          quersumme = (Integer.parseInt(zwischenmErgebnisstring.substring(0, 1))
                  + Integer.parseInt(zwischenmErgebnisstring.substring(1)));
        } else {
          quersumme = zwischenmErgebnis;
        }
        // Quersummen addieren
        mErgebnis += quersumme;
      }
    }
    // Nur Einserstelle berücksichtigen und diese von zehn subtrahieren
    if (mErgebnis > 9) {
      String mErgebnisstring = Integer.toString(mErgebnis);
      mErgebnis = 10 - (Integer.parseInt(mErgebnisstring.substring(mErgebnisstring.length() - 1)));
    }
    // Vergleichswert bestimmen
    return mErgebnis;
  }
}
