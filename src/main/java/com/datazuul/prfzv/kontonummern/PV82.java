package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 82
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6</p>
 *
 * <p>
 * Die Kontonummer ist durch linksbündige Nullenauffüllung 10- stellig darzustellen. Die 10. Stelle ist per Definition die Prüfziffer. Die für die Berechnung relevanten Stellen werden von rechts nach
 * links mit den Ziffern 2, 3, 4, 5, 6 multipliziert. Die weitere Berechnung und die möglichen Ergebnisse entsprechen dem Verfahren 33.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x x x x x x x P
 * Gewichtung:         6 5 4 3 2
 * </pre>
 *
 * <p>
 * Ausnahme:</p>
 *
 * <p>
 * Sind die 3. und 4. Stelle der Kontonummer = 99, so erfolgt die Berechnung nach Verfahren 10.</p>
 *
 * <p>
 * Testkontonummern: 123897, 3199500501</p>
 */
public class PV82 extends PRFZV {

  public PV82() {
  }

  @Override
  public int getVergleichswert() {
    // vorgegebene mGewichtung
    if (mKontonummer.charAt(2) == '9' && mKontonummer.charAt(3) == '9') {
      mGewichtungswerte = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10};
    } // Verfahrensmuster 10
    else {
      mGewichtungswerte = new int[]{2, 3, 4, 5, 6};
    } // Verfahrensmuster 33

    // vorgegebener mModulo-Wert
    mModulo = 11;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 9;

    // mKontonummernummer zehnstellig machen
    while (mKontonummer.length() < 10) {
      mKontonummer = "0" + mKontonummer;
    }

    // Array mGewichtung füllen
    int indexstop = 4; // Verfahrensmuster 33
    if (mGewichtungswerte.length == 9) // Verfahrensmuster 10
    {
      indexstop = 0;
    }

    mGewichtung = new int[9];
    for (int i = 8; i >= indexstop; i--) {
      mGewichtung[i] = mGewichtungswerte[mCounter];
      mCounter++;
    }
    for (int i = 0; i < 9; i++) {
      mErgebnis += (Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]);
    }
    // Vergleichswert bestimmen
    mRest = mErgebnis % mModulo;
    if (mRest == 1 || mRest == 0) {
      mVergleichswert = 0;
    } else {
      mVergleichswert = mModulo - mRest;
    }

    return mVergleichswert;
  }
}
