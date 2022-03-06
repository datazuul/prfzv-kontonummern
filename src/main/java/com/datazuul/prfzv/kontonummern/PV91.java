package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 91 (geändert zum 08.12.2003)
 *
 * <p>
 * FIXME: unteres Berechnung veraltet</p>
 *
 * <p>
 * 1. Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7<br>
 * 2. Modulus 11, Gewichtung 7, 6, 5, 4, 3, 2<br>
 * 3. Modulus 11, Gewichtung 2, 3, 4, 0, 5, 6, 7, 8, 9, A (A = 10)<br>
 * 4. Modulus 11, Gewichtung 2, 4, 8, 5, 10, 9</p>
 *
 * <p>
 * Gemeinsame Hinweise für die Berechnungsvarianten 1 bis 4:</p>
 *
 * <p>
 * Die Kontonummer ist immer 10-stellig. Die einzelnen Stellen der Kontonummer werden von links nach rechts von 1 bis 10 durchnummeriert. Die Stelle 7 der Kontonummer ist die Prüfziffer. Die für die
 * Berechnung relevanten Kundennummern (K) sind von rechts nach links mit den jeweiligen Gewichten zu multiplizieren. Die restliche Berechnung und möglichen Ergebnisse entsprechen dem Verfahren
 * 06.</p>
 *
 * <p>
 * Ergibt die Berechnung nach der ersten beschriebenen Variante einen Prüfzifferfehler, so sind in der angegebenen Reihenfolge weitere Berechnungen mit den anderen Varianten vorzunehmen, bis die
 * Berechnung keinen Prüfzifferfehler mehr ergibt. Kontonummern, die endgültig nicht zu einem richtigen Ergebnis führen, sind nicht prüfbar.</p>
 *
 * <p>
 * Variante 1:</p>
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7</p>
 *
 * <p>
 * Die Stellen 8 bis 10 werden nicht in die Berechnung einbezogen.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   K K K K K K P x x x
 * Gewichtung: 7 6 5 4 3 2
 * </pre>
 *
 * <p>
 * Testkontonummern (richtig): 2974118000, 5281741000, 9952810000<br>
 * Testkontonummern (falsch): 8840017000, 8840023000, 8840041000</p>
 *
 * <p>
 * Variante 2:</p>
 *
 * <p>
 * Modulus 11, Gewichtung 7, 6, 5, 4, 3, 2</p>
 *
 * <p>
 * Die Stellen 8 bis 10 werden nicht in die Berechnung einbezogen.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   K K K K K K P x x x
 * Gewichtung: 2 3 4 5 6 7
 * </pre>
 *
 * <p>
 * Testkontonummern (richtig): 2974117000, 5281770000, 9952812000<br>
 * Testkontonummern (falsch): 8840014000, 8840026000, 8840045000</p>
 *
 * <p>
 * Variante 3:</p>
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 0, 5, 6, 7, 8, 9, A (A = 10)</p>
 *
 * <p>
 * Die Stellen 1 bis 10 werden in die Berechnung einbezogen.</p>
 *
 * <pre>
 * Stellennr.:  1 2 3 4 5 6 7 8 9 A (A= 10)
 * Kontonr.:    K K K K K K P x x x
 * Gewichtung: 10 9 8 7 6 5 0 4 3 2
 * </pre>
 *
 * <p>
 * Testkontonummern (richtig): 8840019000, 8840050000, 8840087000, 8840045000<br>
 * Testkontonummern (falsch): 8840011000, 8840025000, 8840062000</p>
 *
 * <p>
 * Variante 4:</p>
 *
 * <p>
 * Modulus 11, Gewichtung 2, 4, 8, 5, A, 9 (A = 10)</p>
 *
 * <p>
 * Die Stellen 8 bis 10 werden nicht in die Berechnung einbezogen.</p>
 *
 * <pre>
 * Stellennr.: 1 2  3 4 5 6 7 8 9 A (A=10)
 * Kontonr.:   K K  K K K K P x x x
 * Gewichtung: 9 10 5 8 4 2
 * </pre>
 *
 * <p>
 * Testkontonummern (richtig): 8840012000, 8840055000, 8840080000<br>
 * Testkontonummern (falsch): 8840010000, 8840057000</p>
 */
public class PV91 extends PRFZV {

  final int A = 0;
  final int B = 1;
  final int C = 2;
  final int MAX_CHECKS = 3;
  final int[][] mGewichtungen = {{2, 3, 4, 5, 6, 7}, {7, 6, 5, 4, 3, 2}, {2, 3, 4, 0, 5, 6, 7, 8, 9, 10}};
  private int mVerfahren = 0;

  public PV91() {
    mHasMoreChecks = true;
  }

  @Override
  public int getVergleichswert() {
    if (mVerfahren == MAX_CHECKS) {
      mHasMoreChecks = false;
      return -1;
    }
    mGewichtungswerte = mGewichtungen[mVerfahren];

    mModulo = 11;
    mPruefzifferstelle = 6;
    while (mKontonummer.length() < 10) {
      mKontonummer = "0" + mKontonummer;
    }
    int startZuordnung = 0, endBerechnung = 0;
    switch (mVerfahren) {
      case A:
      case B:
        startZuordnung = 6;
        endBerechnung = 6;
        break;
      case C:
        startZuordnung = 10;
        endBerechnung = 10;
        break;
      default:
        break;
    }
    mVerfahren++;
    //System.out.println("Verfahren: " + mVerfahren);
    mGewichtung = new int[10];
    for (int i = startZuordnung - 1; i >= 0; i--) {
      mGewichtung[i] = mGewichtungswerte[mCounter];
      mCounter++;
    }
    mCounter = 0;
    for (int i = 0; i < endBerechnung; i++) {
      mErgebnis += (Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]);
    }
    // Vergleichswert bestimmen
    mRest = mErgebnis % mModulo;
    mErgebnis = 0;
    if (mRest == 1 || mRest == 0) {
      return mVergleichswert = 0;
    } else {
      return mVergleichswert = mModulo - mRest;
    }
  }
}
