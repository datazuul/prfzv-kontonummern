package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 67
 *
 * <p>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2</p>
 *
 * <p>
 * Die Kontonummer ist 10-stellig. Die Berechnung erfolgt wie bei Verfahren 00. Es ist jedoch zu beachten, dass die zweistellige Unterkontonummer (Stellen 9 und 10) nicht in das
 * Prüfzifferberechnungsverfahren mit einbezogen werden darf. Die für die Berechnung relevante siebenstellige Stammnummer befindet sich in den Stellen 1 bis 7, die Prüfziffer in der Stelle 8.</p>
 */
public class PV67 extends PRFZV {

  public PV67() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 1};

    // vorgegebener mModulo-Wert
    mModulo = 10;

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 7;
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
    Integer zwischenmErgebnis;
    String zwischenmErgebnisstring;
    int quersumme;
    for (int i = 0; i < 7; i++) {
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
    // Vergleichswert bestimmen
    return mErgebnis;
  }
}
