package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 32
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7</p>
 *
 * <p>
 * Die Kontonummer ist 10-stellig. Die Stellen 4 bis 9 der Kontonummer werden von rechts nach links mit den Ziffern 2, 3, 4, 5, 6, 7 multipliziert. Die Berechnung und Ergebnisse entsprechen dem
 * Verfahren 06. Die Stelle 10 der Kontonummer ist per Definition die Prüfziffer.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x x x x x x x P
 * Gewichtung: 7 6 5 4 3 2
 * </pre>
 *
 * <p>
 * Testkontonummern: 9141405, 1709107983, 0122116979, 0121114867, 9030101192, 9245500460</p>
 */
public class PV32 extends PRFZV {

  public PV32() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 3, 4, 5, 6, 7};

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
    mGewichtung = new int[6];
    for (int i = 5; i >= 0; i--) {
      mGewichtung[mCounter] = mGewichtungswerte[i];
      mCounter++;
    }
    for (int i = 0; i < 6; i++) {
      mErgebnis += (Integer.parseInt(mKontonummer.substring(i + 3, i + 4)) * mGewichtung[i]);
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
