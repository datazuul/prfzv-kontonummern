package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 19
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 1</p>
 *
 * <p>
 * Die Berechnung und mögliche Ergebnisse entsprechen dem Verfahren 06.</p>
 *
 * <p>
 * Testkontonummern: 0240334000, 0200520016</p>
 */
public class PV19 extends PRFZV {

  public PV19() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 1};

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
      if (mCounter > mGewichtungswerte.length - 1) {
        mCounter = 0;
      }
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
