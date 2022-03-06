package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 21
 *
 * <p>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 (modifiziert)</p>
 *
 * <p>
 * Die Berechnung erfolgt wie bei Verfahren 00. Nach der Addition der Produkte werden neben der Einerstelle jedoch alle Stellen berücksichtigt, indem solange Quersummen gebildet werden, bis ein
 * einstelliger Wert verbleibt. Die Differenz zwischen diesem Wert und dem Wert 10 ist die Prüfziffer.</p>
 */
public class PV21 extends PRFZV {

  public PV21() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 1};

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
    int zwischenmErgebnis;
    for (int i = 0; i < 9; i++) {
      // Multiplikation der mGewichtung mit mKontonummer
      zwischenmErgebnis = ((Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]));
      mErgebnis += (zwischenmErgebnis);
    }

    while (mErgebnis > 9) {
      String mErgebnisstring = Integer.toString(mErgebnis);
      mErgebnis = (Integer.parseInt(mErgebnisstring.substring(0, 1))
              + Integer.parseInt(mErgebnisstring.substring(1)));
    }

    // Vergleichswert bestimmen*/
    return mErgebnis;
  }
}
