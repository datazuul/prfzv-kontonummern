package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 56
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 2, 3, 4</p>
 *
 * <p>
 * Die Stellen 1 bis 9 der Kontonummer werden von rechts nach links mit den Ziffern 2, 3, 4, 5, 6, 7, 2, 3, 4 multipliziert. Die jeweiligen Produkte werden addiert und die Summe durch 11 dividiert.
 * Der Rest wird von 11 abgezogen, das Ergebnis ist die Prüfziffer. Prüfziffer ist die 10. Stelle der Kontonummer.</p>
 *
 * <p>
 * Beispiel 1)</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   0 2 9 0 5 4 5 0 0 P
 * Gewichtung: 4 3 2 7 6 5 4 3 2
 *             0+6+18+0+30+20+20+0+0 = 94 : 11 = 8, Rest 6
 *             11 - 6 = 5
 * </pre>
 *
 * <p>
 * Die Prüfziffer ist 5</p>
 *
 * <p>
 * Bei dem Ergebnis 10 oder 11 ist die Kontonummer ungültig.</p>
 *
 * <p>
 * Beispiel 2)<br>
 * Beginnt eine 10-stellige Kontonummer mit 9, so wird beim Ergebnis 10 die Prüfziffer = 7 und beim Ergebnis 11 die Prüfziffer = 8 gesetzt.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   9 7 1 8 3 0 4 0 3 P
 * Gewichtung: 4 3 2 7 6 5 4 3 2
 *            36 + 21 + 2 + 56 + 18 + 0 + 16 + 0 + 6 = 155 : 11 = 14, Rest 1
 *            11 - 1 = 10
 * </pre>
 *
 * <p>
 * Die Prüfziffer ist 7.</p>
 */
public class PV56 extends PRFZV {

  public PV56() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 3, 4, 5, 6, 7};

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
    mVergleichswert = mModulo - mRest;

    if (Integer.parseInt(mKontonummer.substring(0, 1)) == 9) {

      if (mVergleichswert == 10) {
        mVergleichswert = 7;
      }
      if (mVergleichswert == 11) {
        mVergleichswert = 8;
      }
    } else {
      // Vergleichswert auf ungültigen Wert setzen
      if (mVergleichswert == 10) {
        mVergleichswert = 99;
      }

      // Vergleichswert auf ungültigen Wert setzen
      if (mVergleichswert == 11) {
        mVergleichswert = 99;
      }
    }
    return mVergleichswert;
  }
}
