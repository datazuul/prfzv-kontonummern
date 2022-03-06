package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 15
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5</p>
 *
 * <p>
 * Die Kontonummer ist 10-stellig. Die Berechnung erfolgt wie bei Verfahren 06; es ist jedoch zu beachten, dass nur die Stellen 6 bis 9 in das Prüfzifferberechnungsverfahren einbezogen werden. Die
 * Stelle 10 der Kontonummer ist die Prüfziffer.</p>
 */
public class PV15 extends PRFZV {

  public PV15() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{5, 4, 3, 2};

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
    mGewichtung = new int[10];
    for (int i = 6; i < 10; i++) //	for(int i = mGewichtung.length - 1; i >= 0; i--)
    {
      mGewichtung[i] = mGewichtungswerte[mCounter];
      mCounter++;
      if (mCounter > mGewichtungswerte.length - 1) {
        mCounter = 0;
      }
    }

    //Verfahrensspezifische Logik
    for (int i = 6; i < 10; i++) {
      mErgebnis += (Integer.parseInt(mKontonummer.substring(i - 1, i)) * mGewichtung[i]);
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
