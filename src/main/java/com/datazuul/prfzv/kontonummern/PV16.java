package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 16
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 2, 3, 4</p>
 *
 * <p>
 * Die Berechnung erfolgt wie bei Verfahren 06. Sollte sich jedoch nach der Division der Rest 1 ergeben, so ist die Kontonummer unabhängig vom eigentlichen Berechnungsergebnis richtig, wenn die
 * Ziffern an 10. und 9. Stelle identisch sind.</p>
 */
public class PV16 extends PRFZV {

  public PV16() {
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
    if (mRest == 1) {
      mPruefzifferstelle = 8;
    } else {
      mVergleichswert = mModulo - mRest;
    }

    return mVergleichswert;
  }
}
