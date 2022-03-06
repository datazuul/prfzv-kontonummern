package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 59
 *
 * <p>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2</p>
 *
 * <p>
 * Die Berechnung erfolgt wie bei Verfahren 00; es ist jedoch zu beachten, dass Kontonummern, die kleiner als 9-stellig sind, nicht in die Prüfzifferberechnung einbezogen und als richtig behandelt
 * werden.</p>
 *
 * <p>
 * FIXME: geändert seit 03.12.2001! Untere Berechnung von 2000... validieren!</p>
 */
public class PV59 extends PRFZV {

  public PV59() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 1};

    // vorgegebener mModulo-Wert
    mModulo = 10;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 9;
  }

  @Override
  int getVergleichswert() {
    // mKontonummernummer zehnstellig machen
    if (mKontonummer.length() < 9) {
      mErgebnis = -1;
      while (mKontonummer.length() < 10) {
        mKontonummer = "0" + mKontonummer;
      }
    } else {
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
      Integer zwischenmErgebnis;
      String zwischenmErgebnisstring;
      int quersumme = 0;
      for (int i = 0; i < 9; i++) {
        // Multiplikation der mGewichtung mit mKontonummer
        zwischenmErgebnis = (Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]);
        // Quersumme der MultiplikationsmErgebnisse bilden
        zwischenmErgebnisstring = zwischenmErgebnis.toString();
        if (zwischenmErgebnisstring.length() == 2) {
          quersumme = (Integer.parseInt(zwischenmErgebnisstring.substring(0, 1))
                  + Integer.parseInt(zwischenmErgebnisstring.substring(1)));
        } else {
          quersumme = zwischenmErgebnis;
        }
        // Quersummen addieren
        mErgebnis += quersumme;
      }
      // Nur Einserstelle berücksichtigen und diese von zehn subtrahieren
      if (mErgebnis > 9) {
        String mErgebnisstring = Integer.toString(mErgebnis);
        mErgebnis = 10 - (Integer.parseInt(mErgebnisstring.substring(mErgebnisstring.length() - 1)));
      }
    }
    // Vergleichswert bestimmen
    return mErgebnis;
  }
}
