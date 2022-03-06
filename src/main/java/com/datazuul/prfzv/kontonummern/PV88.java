package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 88
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7</p>
 *
 * <p>
 * Die Kontonummer ist durch linksbündige Nullenauffüllung 10- stellig darzustellen. Die Stellen 4 bis 9 werden von rechts nach links mit den Gewichten 2, 3, 4, 5, 6, 7 multipliziert. Die restliche
 * Berechnung und mögliche Ergebnisse entsprechen dem Verfahren 06. Die Stelle 10 der Kontonummer ist per Definition die Prüfziffer.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   x x x x x x x x x P
 * Gewichtung:       7 6 5 4 3 2
 * </pre>
 *
 *
 * <p>
 * Ausnahme:</p>
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 8</p>
 *
 * <p>
 * Ist die 3. Stelle der Kontonummer = 9, so werden die Stellen 3 bis 9 von rechts nach links mit den Gewichten 2, 3, 4, 5, 6, 7, 8 multipliziert. Die weitere Berechnung erfolgt wie bei Verfahren
 * 06.</p>
 *
 * <p>
 * Testkontonummern: 2525259, 1000500, 90013000, 92525253, 99913003</p>
 */
public class PV88 extends PRFZV {

  public PV88() {
  }

  @Override
  public int getVergleichswert() {
    // vorgegebene mGewichtung
    if (mKontonummer.charAt(2) == '9') {
      mGewichtungswerte = new int[]{2, 3, 4, 5, 6, 7, 8};
    } // Ausnahmefall
    else {
      mGewichtungswerte = new int[]{2, 3, 4, 5, 6, 7};
    } // Regelfall

    // vorgegebener mModulo-Wert
    mModulo = 11;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 9;

    // mKontonummernummer zehnstellig machen
    while (mKontonummer.length() < 10) {
      mKontonummer = "0" + mKontonummer;
    }

    // Array mGewichtung füllen
    int indexstop = 3; // Regelfall
    if (mGewichtungswerte.length == 7) // Ausnahmefall	
    {
      indexstop = 2;
    }

    mGewichtung = new int[9];
    for (int i = 8; i >= indexstop; i--) {
      mGewichtung[i] = mGewichtungswerte[mCounter];
      mCounter++;
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

    return mVergleichswert;
  }
}
