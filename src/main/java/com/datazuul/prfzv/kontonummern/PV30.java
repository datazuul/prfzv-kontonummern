package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 30
 *
 * <p>
 * Modulus 10, Gewichtung 2, 0, 0, 0, 0, 1, 2, 1, 2</p>
 *
 * <p>
 * Die letzte Stelle ist per Definition die Prüfziffer.</p>
 *
 * <p>
 * Die einzelnen Stellen der Kontonummer sind ab der ersten Stelle von links nach rechts mit den Ziffern 2, 0, 0, 0, 0, 1, 2, 1, 2 zu multiplizieren. Die jeweiligen Produkte werden addiert (ohne
 * Quersummenbildung). Die weitere Berechnung erfolgt wie bei Verfahren 00.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x x x x x x x P
 * Gewichtung: 2 0 0 0 0 1 2 1 2
 * </pre>
 */
public class PV30 extends PRFZV {

  public PV30() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 1, 2, 1, 0, 0, 0, 0, 2};

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
    for (int i = 0; i < 9; i++) {
      // Multiplikation der mGewichtung mit mKontonummer
      // Die Gewichtungswerte wurden falsch herum in den Array geschrieben, dann passt die Schleife wieder
      // jetzt wird das Ergebnis nach der multiplikation aufaddiert.
      mErgebnis += ((Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]));
    }
    // Nur Einserstelle ber�cksichtigen und diese von zehn subtrahieren
    if (mErgebnis > 9) {
      String mErgebnisstring = Integer.toString(mErgebnis);
      mErgebnis = 10 - (Integer.parseInt(mErgebnisstring.substring(mErgebnisstring.length() - 1)));
    }
    // Vergleichswert bestimmen
    return mErgebnis;
  }
}
