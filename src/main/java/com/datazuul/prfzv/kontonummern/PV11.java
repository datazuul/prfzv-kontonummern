package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 11
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 10 (modifiziert)</p>
 *
 * <p>
 * Die Berechnung erfolgt wie bei Verfahren 06. Beim Rechenergebnis 10 wird die Null jedoch durch eine 9 ersetzt.</p>
 */
public class PV11 extends PRFZV {

  public PV11() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10};

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
    for (int i = 8; i >= 0; i--) {
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
    }
    if (mRest == 1) {
      mVergleichswert = 9;
    }
    if (mRest != 0 && mRest != 1) {
      mVergleichswert = mModulo - mRest;
    }

    return mVergleichswert;
  }
}
