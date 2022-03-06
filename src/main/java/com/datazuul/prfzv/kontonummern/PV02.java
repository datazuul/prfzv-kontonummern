package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 02
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8, 9, 2</p>
 *
 * <p>
 * Die Stellen der Kontonummer sind von rechts nach links mit den Ziffern 2, 3, 4, 5, 6, 7, 8, 9, 2 zu multiplizieren. Die jeweiligen Produkte werden addiert. Die Summe ist durch 11 zu dividieren. Der
 * verbleibende Rest wird vom Divisor (11) subtrahiert. Das Ergebnis ist die Prüfziffer. Verbleibt nach der Division durch 11 kein Rest, ist die Prüfziffer 0. Ergibt sich als Rest 1, ist die
 * Prüfziffer zweistellig und kann nicht verwendet werden. Die Kontonummer ist dann nicht verwendbar.</p>
 */
public class PV02 extends PRFZV {

  public PV02() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 3, 4, 5, 6, 7, 8, 9};

    // vorgegebener mModulo-Wert
    mModulo = 11;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 9;
  }

  @Override
  int getVergleichswert() {
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
    if (mRest == 1) {
      return -1;
    }
    if (mRest == 0) {
      mVergleichswert = 0;
    } else {
      mVergleichswert = mModulo - mRest;
    }
    return mVergleichswert;
  }
}
