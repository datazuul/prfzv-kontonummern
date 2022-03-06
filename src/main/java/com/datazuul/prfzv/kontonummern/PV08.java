package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 08
 *
 * <p>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2 (modifiziert)</p>
 *
 * <p>
 * Die Berechnung erfolgt wie bei Verfahren 00, jedoch erst ab der Kontonummer 60 000</p>
 */
public class PV08 extends PRFZV {

  public PV08() {
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
    while (mKontonummer.length() < 10) {
      mKontonummer = "0" + mKontonummer;
    }

    //Test ob Kontonummer >=60000
    if (Integer.parseInt(mKontonummer) < 60000) {
      //mErgebnis auf irgendeinen Wert grösser als 9 setzen, somit Prüfziffer immer falsch, weil ja Kontonummer 
      //nicht grösser 60000 war
      System.out.println("Kontonummer kleiner 60000");
      mErgebnis = 100;
    } else {

      // Array mGewichtung f�llen
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
