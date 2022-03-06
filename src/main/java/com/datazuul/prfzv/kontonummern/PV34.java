package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 34
 *
 * <p>
 * Modulus 11, Gewichtung 2, 4, 8, 5, A, 9, 7 (A = 10)</p>
 *
 * <p>
 * Die Kontonummer ist 10-stellig. Es wird das Berechnungsverfahren 28 mit modifizierter Gewichtung angewendet. Die Gewichtung lautet 2, 4, 8, 5, A, 9, 7. Dabei steht der Buchstabe A für den Wert
 * 10.</p>
 *
 * <p>
 * Testkontonummern: 9913000700, 9914001000 </p>
 */
public class PV34 extends PRFZV {

  public PV34() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 4, 8, 5, 10, 9, 7};

    // vorgegebener mModulo-Wert
    mModulo = 11;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 7;
  }

  @Override
  public int getVergleichswert() {
    // mKontonummernummer zehnstellig machen
    while (mKontonummer.length() < 10) {
      mKontonummer = "0" + mKontonummer;
    }
    // Array mGewichtung füllen
    mGewichtung = new int[7];
    for (int i = 6; i >= 0; i--) {
      mGewichtung[i] = mGewichtungswerte[mCounter];
      mCounter++;
    }
    for (int i = 0; i < 7; i++) {
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
