package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 23 (geändert zum 03.09.2001)
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7</p>
 *
 * <p>
 * Das Berechnungsverfahren entspricht dem des Kennzeichens 16, wird jedoch nur auf die ersten sechs Ziffern der Kontonummer angewandt. Die Prüfziffer befindet sich an der 7. Stelle der Kontonummer.
 * Die Stellen 8 bis 10 bleiben ungeprüft.</p>
 *
 * <pre>
 * Stellennr.:  1 2 3 4 5 6 7 8 9 10
 * Kontonummer: x x x x x x P x x x
 * Gewichtung:  7 6 5 4 3 2
 * </pre>
 *
 * <p>
 * Summe geteilt durch 11 = x, Rest<br>
 * Rest = 0: Prüfziffer = 0<br>
 * Rest = 1: Prüfziffer = 6. und 7. Stelle der Kontonummer müssen identisch sein<br>
 * Rest = 2 bis 10: Prüfziffer = 11 minus Rest</p>
 */
public class PV23 extends PRFZV {

  public PV23() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{7, 6, 5, 4, 3, 2};

    // vorgegebener mModulo-Wert
    mModulo = 11;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 6;
  }

  @Override
  public int getVergleichswert() {
    // FIXME: neues Verfahren noch nicht implementiert (s. Beschreibung oben)!!!
    
    // altes Verfahren:
    
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

    for (int i = 0; i < 6; i++) {
      mErgebnis += (Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]);
    }
    // Vergleichswert bestimmen
    mRest = mErgebnis % mModulo;
    if (mRest == 0) {
      mVergleichswert = 0;
    }
    if (mRest == 1) {
      mPruefzifferstelle = 5;
    }
    if (mRest == 2) {
      mVergleichswert = mModulo - mRest;
    }
    return mVergleichswert;
  }
}
