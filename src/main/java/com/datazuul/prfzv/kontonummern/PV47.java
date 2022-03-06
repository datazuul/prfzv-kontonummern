package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 47
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6</p>
 *
 * <p>
 * Die Kontonummer ist 10-stellig. Die Stellen 4 bis 8 der Kontonummer werden von rechts nach links mit den Ziffern 2, 3, 4, 5, 6 multipliziert. Die restliche Berechnung und Ergebnisse entsprechen dem
 * Verfahren 06. Die Stelle 9 der Kontonummer ist per Definition die Prüfziffer.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x x x x x x P x
 * Gewichtung: 6 5 4 3 2
 * </pre>
 *
 * <p>
 * Testkontonummern: 1018000, 1003554450</p>
 */
public class PV47 extends PRFZV {

  public PV47() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 3, 4, 5, 6};

    // vorgegebener mModulo-Wert
    mModulo = 11;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 8;
  }

  @Override
  public int getVergleichswert() {
    // mKontonummernummer zehnstellig machen
    while (mKontonummer.length() < 10) {
      mKontonummer = "0" + mKontonummer;
    }

    // Array mGewichtung füllen
    mGewichtung = new int[8];
    for (int i = 7; i >= 3; i--) {
      mGewichtung[i] = mGewichtungswerte[mCounter];
      mCounter++;
    }
    for (int i = 0; i < 8; i++) {
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
