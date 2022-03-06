package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 01
 *
 * <p>
 * Modulus 10, Gewichtung 3, 7, 1, 3, 7, 1, 3, 7, 1</p>
 *
 * <p>
 * Die Stellen der Kontonummer sind von rechts nach links mit den Ziffern 3, 7, 1, 3, 7, 1 usw. zu multiplizieren. Die jeweiligen Produkte werden addiert. Nach der Addition bleiben außer der
 * Einerstelle alle anderen Stellen unberücksichtigt. Die Einerstelle wird von dem Wert 10 subtrahiert. Das Ergebnis ist die Prüfziffer (10. Stelle der Kontonummer). Ergibt sich nach der Subtraktion
 * der Rest 10, ist die Prüfziffer 0.</p>
 */
public class PV01 extends PRFZV {

  public PV01() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{3, 7, 1};

    // vorgegebener mModulo-Wert
    mModulo = 10;

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

    int summe = 0;
    for (int i = 0; i < 9; i++) {
      // Multiplikation der mGewichtung mit mKontonummer
      summe += ((Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]));
    }

    mErgebnis = summe;
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
