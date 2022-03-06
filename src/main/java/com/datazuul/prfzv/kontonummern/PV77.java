package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 77
 *
 * <p>
 * Modulus 11, Gewichtung 1, 2, 3, 4, 5</p>
 *
 * <p>
 * Die Kontonummer ist 10-stellig. Die für die Berechnung relevanten Stellen 6 bis 10 werden von rechts nach links mit den Ziffern 1, 2, 3, 4, 5 multipliziert. Die Produkte werden addiert. Die Summe
 * ist durch 11 zu dividieren.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x x x 4 7 6 7 8
 * Gewichtung:           5 4 3 2 1
 *                       20 + 28 + 18 + 14 + 8 = 88
 *                       88 : 11 = 8 Rest 0
 * </p>
 *
 * <p>Verbleibt nach der Division der Summe durch 11 ein Rest, ist
 * folgende neue Berechnung durchzuführen:</p>
 *
 * <p>Modulus 11, Gewichtung 5, 4, 3, 4, 5</p>
 *
 * <pre>
 * Beispiel:
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x x x 4 7 6 7 1
 * Gewichtung:           5 4 3 4 5
 *                       20 + 28 + 18 + 28 + 5 = 99
 *                       99 : 11 = 9 Rest 0
 * </pre>
 *
 * <p>
 * Ergibt sich bei der erneuten Berechnung wiederum ein Rest, dann ist die Kontonummer falsch.</p>
 *
 * <p>
 * Erläuterung:</p>
 *
 * <p>
 * x = weitere Ziffern der Kontonummer, die jedoch nicht in die Berechnung einbezogen werden.</p>
 *
 * <p>
 * Testkontonummern: 10338, 13844, 65354, 69258</p>
 */
public class PV77 extends PRFZV {

  public PV77() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{1, 2, 3, 4, 5};

    // vorgegebener mModulo-Wert
    mModulo = 11;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 8;
  }

  @Override
  public int getVergleichswert() {
    // mKontonummernummer zehnstellig machen
    while (mKontonummer.length() < 10) {
      mKontonummer = "0" + mKontonummer;
    }

    // Array mGewichtung f�llen
    mGewichtung = new int[8];
    for (int i = 7; i >= 2; i--) {
      mGewichtung[i] = mGewichtungswerte[mCounter];
      mCounter++;
    }
    for (int i = 0; i < 8; i++) {
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
