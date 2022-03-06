package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 13
 *
 * <p>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1</p>
 *
 * <p>
 * Die Berechnung erfolgt wie bei Verfahren 00. Die für die Berechnung relevante sechsstellige Grundnummer befindet sich in den Stellen 2 bis 7, die Prüfziffer in Stelle 8 (von links nach rechts
 * gezählt). Die zweistellige Unterkontonummer (Stellen 9 und 10) darf nicht in das Prüfzifferberechnungsverfahren einbezogen werden. Ist die Unterkontonummer »00», kommt es vor, dass sie nicht
 * angegeben ist. Ergibt die erste Berechnung einen Prüfzifferfehler, wird empfohlen, die Prüfzifferberechnung ein zweites Mal durchzuführen und dabei die »gedachte« Unterkontonummer 00 an die Stellen
 * 9 und 10 zu setzen und die vorhandene Kontonummer vorher um zwei Stellen nach links zu verschieben.</p>
 */
public class PV13 extends PRFZV {

  public PV13() {
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
    String mKontonummerSpeicher = mKontonummer;
    while (mKontonummer.length() < 10) {
      mKontonummer = "0" + mKontonummer;
    }

    // Array mGewichtung füllen
    mGewichtung = new int[8];
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

    //i auf 1 setzen, weil 
    for (int i = 1; i < 7; i++) {
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
    // Nur Einserstelle ber�cksichtigen und diese von zehn subtrahieren
    if (mErgebnis > 9) {
      String mErgebnisstring = Integer.toString(mErgebnis);
      mErgebnis = 10 - (Integer.parseInt(mErgebnisstring.substring(mErgebnisstring.length() - 1)));
      if (mErgebnis >= 10) {
        mErgebnis = 0;
      }
    }

    /*Wenn erste Prüfzifferberechnung einen Fehler bringt, muss evtl. geprüft werden ob die Unterkontonummer		
		  angegeben ist, wenn nicht, dann muss 00 an die stellen 9,10 der kto.nr. gesetzt werden und dann das 
		  verfahren nochmal getestet werden.*/
    // !!!!!!!!!! ANSONSTEN NORMALE RÜCKGABE DES mErgebnis !!!!!!!!!!
    if (mErgebnis != (Integer.parseInt(mKontonummer.substring(7, 8)))) {
      mKontonummer = mKontonummerSpeicher;
      mKontonummer = mKontonummer + "00";
      // gleicher Algorithmus wie oben, PRFZV soll ja laut Doku einfach nochmal durchgeführt werden
      while (mKontonummer.length() < 10) {
        mKontonummer = "0" + mKontonummer;
      }

      for (int i = 1; i < 7; i++) {
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
        if (mErgebnis >= 10) {
          mErgebnis = 0;
        }
      }
    }
    // Vergleichswert bestimmen
    return mErgebnis;
  }
}
