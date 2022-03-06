package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 43
 *
 * <p>
 * Modulus 10, Gewichtung 1, 2, 3, 4, 5, 6, 7, 8, 9</p>
 *
 * <p>
 * Die Kontonummer ist 10-stellig. Die Stellen 1 bis 9 der Kontonummer werden von rechts nach links mit den Faktoren 1, 2, 3, 4, 5, 6, 7, 8, 9 multipliziert. Die Summe der Produkte wird durch den Wert
 * 10 dividiert. Der Rest der Division wird vom Divisor subtrahiert. Die Differenz ist die Prüfziffer. Ergibt die Berechnung eine Differenz von 10, lautet die Prüfziffer 0. Die Stelle 10 der
 * Kontonummer ist per Definition die Prüfziffer.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x x x x x x x P
 * Gewichtung: 9 8 7 6 5 4 3 2 1
 * </pre>
 *
 * <p>
 * Testkontonummern: 6135244, 9516893476</p>
 */
public class PV43 extends PRFZV {

  public PV43() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    // vorgegebener mModulo-Wert
    mModulo = 10;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 9;
  }

  @Override
  public int getVergleichswert() {
    // mKontonummernummer zehnstellig machen
    while (mKontonummer.length() < 10) {
      mKontonummer = "0" + mKontonummer;
    }

    // Array mGewichtung füllen
    mGewichtung = new int[9];
    for (int i = mGewichtung.length - 1; i >= 0; i--) {
      mGewichtung[i] = mGewichtungswerte[mCounter];
      mCounter++;
    }
    for (int i = 0; i < 9; i++) {
      mErgebnis += (Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]);
    }
    // Vergleichswert bestimmen
    mRest = mErgebnis % mModulo;
    if (mRest == 0) {
      mVergleichswert = 0;
    } else {
      mVergleichswert = mModulo - mRest;
    }

    return mVergleichswert;
  }
}
