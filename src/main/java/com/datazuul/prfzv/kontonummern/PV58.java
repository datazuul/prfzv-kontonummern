package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 58
 * 
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 0, 0, 0, 0</p>
 *
 * <p>
 * Die Kontonummer (mindestens 6-stellig) ist durch linksbündige Nullenauffüllung 10-stellig darzustellen. Danach ist die 10. Stelle die Prüfziffer. Die Stellen 5 bis 9 werden von rechts nach links
 * mit den Ziffern 2, 3, 4, 5, 6 multipliziert. Die restliche Berechnung und die Ergebnisse entsprechen dem Verfahren 02.</p>
 *
 * <pre>
 * Beispiel:
 * Stellennr.: 1 2 3 4 5 6 7 8 9 P
 * Kontonr.:   1 8 0 0 2 9 3 3 7 7
 * Wichtung:   0 0 0 0 6 5 4 3 2
 *             0 +0 +0 +0 +12 +45 +12 +9 +14 =92
 *             92: 11 = 8, Rest 4
 *             11 - 4 = 7
 *             P = 7
 * </pre>
 *
 * <p>
 * Ergibt die Division einen Rest von 0, so ist die Prüfziffer = 0. Bei einem Rest von 1 ist die Kontonummer falsch.</p>
 *
 * <p>
 * Testkontonummern: 1800881120, 9200654108, 1015222224, 3703169668</p>
 * 
 * <p>FIXME: geändert seit 04.03.2002!!! Untere Berechnung veraltet!!!</p>
 */
public class PV58 extends PRFZV {

  public PV58() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 3, 4, 5, 6};

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

    for (int i = 4; i < 9; i++) {
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
