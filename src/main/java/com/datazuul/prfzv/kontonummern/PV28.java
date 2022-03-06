package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 28
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8</p>
 *
 * <p>
 * Die Kontonummer ist 10-stellig. Die zweistellige Unterkontonummer (Stellen 9 und 10) wird nicht in das Berechnungsverfahren einbezogen. Die für die Berechnung relevanten Stellen 1 bis 7 werden von
 * rechts nach links mit den Ziffern 2, 3, 4, 5, 6, 7, 8 multipliziert. Die 8. Stelle ist die Prüfziffer. Die Berechnung und Ergebnisse entsprechen dem Verfahren 06.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x x x x x P x x
 * Gewichtung: 8 7 6 5 4 3 2
 * </pre>
 *
 * <p>
 * Wird als Rest eine 0 oder eine 1 ermittelt, so lautet die Prüfziffer 0.</p>
 *
 * <p>
 * Testkontonummern: 19999000, 9130000201</p>
 */
public class PV28 extends PRFZV {

  public PV28() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 3, 4, 5, 6, 7, 8};

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
