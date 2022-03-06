package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 44
 *
 * <p>
 * Modulus 11, Gewichtung 2, 4, 8, 5, A, 0, 0, 0, 0 (A = 10)</p>
 *
 * <p>
 * Die Berechnung erfolgt wie bei Verfahren 33.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 10
 * Kontonr.:   x x x x x x x x x P
 * Gewichtung: 0 0 0 0 A 5 8 4 2 (A = 10)
 * </pre>
 *
 * <p>
 * Testkontonummern: 889006, 2618040504
 */
public class PV44 extends PRFZV {

  public PV44() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 4, 8, 5, 10, 0, 0, 0, 0};

    // vorgegebener mModulo-Wert
    mModulo = 11;

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
    if (mRest == 1 || mRest == 0) {
      mVergleichswert = 0;
    } else {
      mVergleichswert = mModulo - mRest;
    }

    return mVergleichswert;
  }
}
