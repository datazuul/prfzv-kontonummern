package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 06
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7 (modifiziert)</p>
 *
 * <p>
 * Die einzelnen Stellen der Kontonummer sind von rechts nach links mit den Ziffern 2, 3, 4, 5, 6, 7, 2, 3 ff. zu multiplizieren. Die jeweiligen Produkte werden addiert. Die Summe ist durch 11 zu
 * dividieren. Der verbleibende Rest wird vom Divisor (11) subtrahiert. Das Ergebnis ist die Prüfziffer. Ergibt sich als Rest 1, findet von dem Rechenergebnis 10 nur die Einerstelle (0) als Prüfziffer
 * Verwendung. Verbleibt nach der Division durch 11 kein Rest, dann ist auch die Prüfziffer 0. Die Stelle 10 der Kontonummer ist die Prüfziffer.</p>
 *
 * <p>
 * Testkontonummern: 94012341, 5073321010 </p>
 */
public class PV06 extends PRFZV {

  public PV06() {
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
    System.out.println(mRest);
    if (mRest == 1 || mRest == 0) {
      mVergleichswert = 0;
    } else {
      mVergleichswert = mModulo - mRest;
    }

    return mVergleichswert;
  }
}
