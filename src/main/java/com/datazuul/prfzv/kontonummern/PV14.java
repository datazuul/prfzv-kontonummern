package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 14
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7</p>
 *
 * <p>
 * Die Berechnung erfolgt wie bei Verfahren 02. Es ist jedoch zu beachten, dass die zweistellige Kontoart nicht in das Prüfzifferberechnungsverfahren mit einbezogen wird. Die Kontoart belegt die
 * Stellen 2 und 3, die zu berechnende Grundnummer die Stellen 4 bis 9. Die Prüfziffer befindet sich in Stelle 10.</p>
 */
public class PV14 extends PRFZV {

  public PV14() {
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
    // Berechnung ist vermutlich richtig - aber es geht aus Doku nicht hervor, was mit Stelle 1 der mKontonummernr. geschieht
    // Sie wird deshalb vernachlässigt (Grundnummer (?) vermutlich die zu verwendenden Stellen der mKontonummernr.)

    for (int i = 3; i < mKontonummer.length() - 4; i++) {
      mErgebnis += (Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]);
    }
    // Vergleichswert bestimmen
    mRest = mErgebnis % mModulo;
    if (mRest == 0) {
      mVergleichswert = 0;
    }
    if (mRest == 1) {
      return -1;
    } else {
      mVergleichswert = mModulo - mRest;
    }

    return mVergleichswert;
  }
}
