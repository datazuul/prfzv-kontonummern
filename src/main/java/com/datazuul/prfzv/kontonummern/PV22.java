package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 22
 *
 * <p>
 * Modulus 10, Gewichtung 3, 1, 3, 1, 3, 1, 3, 1, 3</p>
 *
 * <p>
 * Die einzelnen Stellen der Kontonummer sind von rechts nach links mit den Ziffern 3, 1, 3, 1 usw. zu multiplizieren. Von den jeweiligen Produkten bleiben die Zehnerstellen unberücksichtigt. Die
 * verbleibenden Zahlen (Einerstellen) werden addiert. Die Differenz bis zum nächsten Zehner ist die Prüfziffer. </p>
 */
public class PV22 extends PRFZV {

  public PV22() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{3, 1};

    // vorgegebener mModulo-Wert
    mModulo = 10;

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

    int summe = 0;
    for (int i = 0; i < 9; i++) {
      // Multiplikation der mGewichtung mit mKontonummer
      summe += ((Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]));
      String mErgebnisstring = Integer.toString(summe);
      mErgebnis += (Integer.parseInt(mErgebnisstring.substring(mErgebnisstring.length() - 1)));
    }

    // Nur Einserstelle berücksichtigen und diese von zehn subtrahieren
    if (mErgebnis > 9) {
      String mErgebnisstring = Integer.toString(mErgebnis);
      mErgebnis = 10 - (Integer.parseInt(mErgebnisstring.substring(mErgebnisstring.length() - 1)));
      if (mErgebnis >= 10) {
        mErgebnis = 0;
      }
    }
    // Vergleichswert bestimmen
    return mErgebnis;
  }
}
