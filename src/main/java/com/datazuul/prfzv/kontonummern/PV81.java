/*
 * $Id: PV81.java,v 1.5 2001/02/01 08:33:52 reichinger Exp $
 */
package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 81 (geändert zum 06.09.2004)
 *
 * <p>
 * FIXME: untere Berechnung veraltet</p>
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7</p>
 *
 * <p>
 * Die Kontonummer ist durch linksbündige Nullenauffüllung 10- stellig darzustellen. Die 10. Stelle ist per Definition die Prüfziffer. Die für die Berechnung relevanten Stellen werden von rechts nach
 * links mit den Ziffern 2, 3, 4, 5, 6, 7 multipliziert. Die weitere Berechnung und die möglichen Ergebnisse entsprechen dem Verfahren 32.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x x x x x x x P
 * Gewichtung:       7 6 5 4 3 2
 * </pre>
 *
 * <p>
 * Testkontonummern: 0646440, 1359100</p>
 *
 * <p>
 * Ausnahme:</p>
 *
 * <p>
 * Ist nach linksbündiger Auffüllung mit Nullen auf 10 Stellen die 3. Stelle der Kontonummer = 9 (Sachkonten), so erfolgt die Berechnung gemäß der Ausnahme in Methode 51 mit den gleichen Ergebnissen
 * und Testkontonummern.</p>
 */
public class PV81 extends PRFZV {

  public PV81() {
  }

  @Override
  public int getVergleichswert() {
    // vorgegebene mGewichtung
    if (mKontonummer.charAt(2) != '9') {
      mGewichtungswerte = new int[]{2, 3, 4, 5, 6, 7}; // Verfahrensmuster 32
    } else {
      mGewichtungswerte = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10};
    }	// Verfahrensmuster 10

    // vorgegebener mModulo-Wert
    mModulo = 11;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 9;

    // mKontonummernummer zehnstellig machen
    while (mKontonummer.length() < 10) {
      mKontonummer = "0" + mKontonummer;
    }

    // Array mGewichtung füllen
    int indexstop = 3; // Verfahrensmuster 32
    if (mGewichtungswerte.length == 9) // Verfahrensmuster 10
    {
      indexstop = 0;
    }

    mGewichtung = new int[9];
    for (int i = 8; i >= indexstop; i--) {
      mGewichtung[i] = mGewichtungswerte[mCounter];
      mCounter++;
    }
    // In Doku werden 2 Verfahrensmuster angegeben, diese unterscheiden sich
    // jedoch nicht! Daher keine Berücksichtigung des Ausnahmefalls

    for (int i = 0; i < 9; i++) {
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
