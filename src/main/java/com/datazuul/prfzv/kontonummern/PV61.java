package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 61
 *
 * <p>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1, 2</p>
 *
 * <pre>
 * Darstellung der Kontonummer:
 * B B B S S S S P A U (10-stellig)
 * B = Betriebsstellennummer
 * S = Stammnummer
 * P = Prüfziffer
 * A = Artziffer
 * U = Unternummer
 * </pre>
 *
 * <p>
 * Ausnahme:</p>
 *
 * <p>
 * Ist die Artziffer (neunte Stelle der Kontonummer) eine 8, so werden die neunte und zehnte Stelle der Kontonummer in die Prüfzifferermittlung einbezogen.<br>
 * Die Berechnung erfolgt dann über Betriebsstellennummer, Stammnummer, Artziffer und Unternummer mit der Gewichtung 2, 1, 2, 1, 2, 1, 2, 1, 2.</p>
 *
 * <pre>
 * Beispiel 1:
 * Stellennr.: B B B S S S S P A U
 * Kontonr.:   2 0 6 3 0 9 9   0 0
 * Gewichtung: 2 1 2 1 2 1 2
 *             4 + 0 + 3 + 3 + 0 + 9 + 9 = 28
 *                    (Q)             (Q)     (Q = Quersumme)
 * </pre>
 *
 * <p>
 * Die Einerstelle wird vom Wert 10 subtrahiert (10 - 8 = 2).</p>
 *
 * <p>
 * Die Prüfziffer ist in diesem Fall die 2 und die vollständige Kontonummer lautet: 2 0 6 3 0 9 9 2 0 0</p>
 *
 * <pre>
 * Beispiel 2:
 * Stellennr.: B B B S S S S P A U
 * Kontonr.:   0 2 6 0 7 6 0   8 1
 * Gewichtung: 2 1 2 1 2 1 2   1 2
 *             0 + 2 + 3 + 0 + 5 + 6 + 0 + 8 + 2 = 26
 *                    (Q)     (Q)            (Q = Quersumme)
 * </pre>
 *
 * <p>
 * Die Einerstelle wird vom Wert 10 subtrahiert (10 - 6 = 4).</p>
 *
 * <p>
 * Die Prüfziffer ist in diesem Fall die 4 und die vollständige Kontonummer lautet: 0 2 6 0 7 6 0 4 8 1 </p>
 */
public class PV61 extends PRFZV {

  public PV61() {
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
    mGewichtung = new int[10];
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

    //Ausnahmeregelung
    if (Integer.parseInt(mKontonummer.substring(8, 9)) == 8) {
      for (int i = 0; i < 9; ++i) {
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
      for (int i = 8; i < 10; i++) {
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
    } else {
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
