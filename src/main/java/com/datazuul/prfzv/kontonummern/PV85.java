package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 85
 *
 * <p>
 * Die Kontonummer ist immer 10-stellig. Die für die Berechnung relevante Kundennummer (K) befindet sich bei der Methode A in den Stellen 4 bis 9 der Kontonummer und bei den Methoden B + C in den
 * Stellen 5 bis 9, die Prüfziffer in Stelle 10 der Kontonummer.</p>
 *
 * <p>
 * Methode A:</p>
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7</p>
 *
 * <p>
 * Die Berechnung und mögliche Ergebnisse entsprechen dem Verfahren 06.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x K K K K K K P
 * Gewichtung:       7 6 5 4 3 2
 * </pre>
 *
 * <p>
 * Testkontonummern: 0001156071, 0001156136</p>
 *
 * <p>
 * Ergibt die Berechnung der Prüfziffer nach der Methode A einen Prüfzifferfehler, ist eine weitere Berechnung mit der Methode B vorzunehmen.</p>
 *
 * <p>
 * Methode B:</p>
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6</p>
 *
 * <p>
 * Die Berechnung und mögliche Ergebnisse entsprechen dem Verfahren 33.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x x K K K K K P
 * Gewichtung:         6 5 4 3 2
 * </pre>
 *
 * <p>
 * Testkontonummer: 0000156078</p>
 *
 * <p>
 * Ergibt auch die Berechnung der Prüfziffer nach Methode B einen Prüfzifferfehler, ist eine weitere Berechnung mit der Methode C vorzunehmen.</p>
 *
 * <p>
 * Methode C:</p>
 *
 * <p>
 * Kontonummern, die bis zur Methode C gelangen und in der 10. Stelle eine 7, 8 oder 9 haben, sind ungültig.</p>
 *
 * <p>
 * Modulus 7, Gewichtung 2, 3, 4, 5, 6</p>
 *
 * <p>
 * Das Berechnungsverfahren entspricht Methode B. Die Summe der Produkte ist jedoch durch 7 zu dividieren. Der verbleibende Rest wird vom Divisor (7) subtrahiert. Das Ergebnis ist die Prüfziffer.
 * Verbleibt kein Rest, ist die Prüfziffer 0.</p>
 *
 * <p>
 * Testkontonummer: 0000156071</p>
 *
 * <p>
 * Ausnahme:</p>
 *
 * <p>
 * Sind die 3. und 4. Stelle der Kontonummer = 99, so ist folgende Prüfzifferberechnung maßgebend:</p>
 *
 * <p>
 * Modulus: 11, Gewichtung 2, 3, 4, 5, 6, 7, 8</p>
 *
 * <p>
 * Die für die Berechnung relevanten Stellen 3 bis 9 der Kontonummer werden von rechts nach links mit den Ziffern 2, 3, 4, 5, 6, 7, 8 multipliziert. Die weitere Berechnung und möglichen Ergebnisse
 * entsprechen dem Verfahren 02.</p>
 *
 * <p>
 * Testkontonummer: 3199100002</p>
 */
public class PV85 extends PRFZV {

  public PV85() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 3, 4, 5, 6};

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
    if (mRest == 1 || mRest == 0) {
      mVergleichswert = 0;
    } else {
      mVergleichswert = mModulo - mRest;
    }
    if (mVergleichswert != getPruefziffer()) {
      mRest = mErgebnis % 7;
      if (mRest == 0) {
        mVergleichswert = 0;
      } else {
        mVergleichswert = 7 - mRest;
      }
    }
    return mVergleichswert;
  }
}
