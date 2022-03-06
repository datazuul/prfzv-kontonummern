package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 64
 *
 * <p>
 * Modulus 11, Gewichtung 9, 10, 5, 8, 4, 2.</p>
 *
 * <p>
 * Die Kontonummer ist 10-stellig. Die für die Berechnung relevanten Stellen der Kontonummer befinden sich in den Stellen 1 bis 6 und werden von links nach rechts mit den Ziffern 9, 10, 5, 8, 4, 2
 * multipliziert. Die weitere Berechnung und Ergebnisse entsprechen dem Verfahren 06. Die Prüfziffer befindet sich in Stelle 7 der Kontonummer.</p>
 *
 * <p>
 * Testkontonummern: 1206473010, 5016511020</p>
 */
public class PV64 extends PRFZV {

  public PV64() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{9, 10, 5, 8, 4, 2};

    // vorgegebener mModulo-Wert
    mModulo = 11;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 6;

    // Array mGewichtung füllen
    mGewichtung = new int[6];
    for (int i = 0; i <= 5; i++) {
      mGewichtung[i] = mGewichtungswerte[i];
    }
  }

  @Override
  public int getVergleichswert() {
    for (int i = 0; i <= 5; i++) {
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
