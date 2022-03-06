package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 37
 *
 * <p>
 * Modulus 11, Gewichtung 2, 4, 8, 5, A (A = 10)</p>
 *
 * <p>
 * Die Kontonummer ist 10-stellig. Die Stellen 5 bis 9 der Kontonummer werden von rechts nach links mit den Faktoren 2, 4, 8, 5, A multipliziert. Dabei steht der Buchstabe A für den Wert 10. Die
 * restliche Berechnung und Ergebnisse entsprechen dem Verfahren 06. Die Stelle 10 der Kontonummer ist per Definition die Prüfziffer.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x x x x x x x P
 * Gewichtung: A 5 8 4 2
 * </pre>
 *
 * <p>
 * Testkontonummern: 624315, 632500</p>
 */
public class PV37 extends PRFZV {

  public PV37() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 4, 8, 5, 10};

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
    for (int i = 8; i >= 4; i--) {
      mGewichtung[i] = mGewichtungswerte[mCounter];
      mCounter++;
    }
    for (int i = 4; i < 9; i++) {
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
