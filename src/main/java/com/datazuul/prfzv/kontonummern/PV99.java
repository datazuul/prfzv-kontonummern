package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 99 (gültig seit 04.03.2002)
 *
 * <p>
 * FIXME: untere Berechnung veraltet?</p>
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 7, 2, 3, 4</p>
 *
 * <p>
 * Die Berechnung erfolgt wie bei Verfahren 06.</p>
 *
 * <p>
 * Ausnahmen:</p>
 *
 * <p>
 * Kontonr.:0396000000 bis 0499999999</p>
 *
 * <p>
 * Für diese Kontonummern ist keine Prüfzifferberechnung möglich. Sie sind als richtig anzusehen.</p>
 *
 * <p>
 * Testkontonummern: 0068007003, 0847321750</p>
 */
public class PV99 extends PRFZV {

  public PV99() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 3, 4, 5, 6, 7, 8};

    // vorgegebener mModulo-Wert
    mModulo = 11;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 9;
  }

  @Override
  public int getVergleichswert() {
    // mKontonummernummer zehnstellig machen
    int mCounter = 0;

    while (mKontonummer.length() < 10) {
      mKontonummer = "0" + mKontonummer;
    }

    if (mKontonummer.charAt(2) == '9') {
      mGewichtung = new int[7];
      for (int i = 6; i >= 0; i--) {
        mGewichtung[i] = mGewichtungswerte[mCounter];
        //	System.out.println("Gewichtung: "+mGewichtung[i] +"  mCounter: "+mCounter+ " i: "+i);
        mCounter++;
      }
      for (int i = 0; i < 7; i++) {
        mErgebnis += (Integer.parseInt(mKontonummer.substring(i + 2, i + 3)) * mGewichtung[i]);
      }
      //	System.out.println("Kontonummerteil: "+(Integer.parseInt(mKontonummer.substring(i + 2, i + 3)))+ " * Gewichtung "+mGewichtung[i]);}
    } else {
      // Array mGewichtung füllen
      mGewichtung = new int[6];
      for (int i = 5; i >= 0; i--) {
        mGewichtung[i] = mGewichtungswerte[mCounter];
        mCounter++;
      }
      for (int i = 0; i < 6; i++) {
        mErgebnis += (Integer.parseInt(mKontonummer.substring(i + 3, i + 4)) * mGewichtung[i]);
      }
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
