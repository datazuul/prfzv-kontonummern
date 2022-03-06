package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 73 (geändert zum 06.12.2004)
 *
 * <p>
 * FIXME: Berechnung unten veraltet!</p>
 *
 * <p>
 * Die Kontonummer ist durch linksbündiges Auffüllen mit Nullen 10-stellig darzustellen. Die 10. Stelle der Kontonummer ist die Prüfziffer.</p>
 *
 * <p>
 * Variante 1:</p>
 *
 * <p>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2, 1</p>
 *
 * <p>
 * Die Stellen 4 bis 9 der Kontonummer werden von rechts nach links mit den Ziffern 2, 1, 2, 1, 2, 1 multipliziert. Die Berechnung und Ergebnisse entsprechen dem Verfahren 00.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   X X X X X X X X X P
 * Gewichtung:       1 2 1 2 1 2
 * </pre>
 *
 * <p>
 * Testkontonummern: richtig: 0003503398, 0001340967<br>
 * falsch: 0003503391, 0001340966</p>
 *
 * <p>
 * Führt die Berechnung nach Variante 1 zu einem Prüfzifferfehler, ist eine weitere Berechnung nach Variante 2 vorzunehmen:</p>
 *
 * <p>
 * Variante 2:</p>
 *
 * <p>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2</p>
 *
 * <p>
 * Das Berechnungsverfahren entspricht Variante 1, es ist jedoch zu beachten, dass nur die Stellen 5 bis 9 in das Prüfziffernberechnungsverfahren einbezogen werden.</p>
 *
 * <p>
 * Testkontonummern: richtig: 0003503391, 0001340968<br>
 * falsch: 0003503392, 0001340966</p>
 *
 * <p>
 * Führt die Berechnung auch nach Variante 2 zu einem Prüfzifferfehler, ist die Berechnung nach Variante 3 vorzunehmen:</p>
 *
 * <p>
 * Variante 3</p>
 *
 * <p>
 * Modulus 7, Gewichtung 2, 1, 2, 1, 2</p>
 *
 * <p>
 * Das Berechnungsverfahren entspricht Variante 2. Die Summe der Produkt-Quersummen ist jedoch durch 7 zu dividieren. Der verbleibende Rest wird vom Divisor (7) subtrahiert. Das Ergebnis ist die
 * Prüfziffer. Verbleibt nach der Division kein Rest, ist die Prüfziffer = 0</p>
 *
 * <p>
 * Testkontonummern: richtig: 0003503392, 0001340966, 123456<br>
 * falsch: 121212, 987654321</p>
 *
 * <p>
 * Ausnahme:</p>
 *
 * <p>
 * Ist nach linksbündiger Auffüllung mit Nullen auf 10 Stellen die 3. Stelle der Kontonummer = 9 (Sachkonten), so erfolgt die Berechnung gemäß der Ausnahme in Methode 51 mit den gleichen Ergebnissen
 * und Testkontonummern.</p>
 */
public class PV73 extends PRFZV {

  public PV73() {
  }

  @Override
  int getVergleichswert() {
    // vorgegebene mGewichtung
    if (is_third_a_nine() == false) {
      mGewichtungswerte = new int[]{2, 1, 2, 1, 2, 1};
    } else {
      mGewichtungswerte = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10};
    }

    // vorgegebener mModulo-Wert
    if (is_third_a_nine() == false) {
      mModulo = 10;
    } else {
      mModulo = 11;
    }

    // Index der Prüfzifferstelle (ab 0 beginnend)
    mPruefzifferstelle = 9;

    // Array mGewichtung füllen
    int indexstop = 3;
    if (is_third_a_nine() == true) {
      indexstop = 0;
    }

    mGewichtung = new int[9];
    for (int i = 8; i >= indexstop; i--) {
      mGewichtung[i] = mGewichtungswerte[mCounter];
      mCounter++;
    }

    /* // mKontonummernummer zehnstellig machen
           while(mKontonummer.length() < 10) {mKontonummer = "0" + mKontonummer;} */
    // Normalfall: 3. Stelle der mKontonummernummer ist keine "9"
    if (is_third_a_nine() == false) {
      // mKontonummernummer zehnstellig machen
      while (mKontonummer.length() < 10) {
        mKontonummer = "0" + mKontonummer;
      }

      Integer zwischenmErgebnis;
      String zwischenmErgebnisstring;
      int quersumme;
      for (int i = 3; i < 9; i++) {
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
      mVergleichswert = mErgebnis;
    } // Ausnahmefall: 3. Stelle der mKontonummernummer ist eine "9"
    else {
      // mKontonummernummer zehnstellig machen
      while (mKontonummer.length() < 10) {
        mKontonummer = "0" + mKontonummer;
      }

      for (int i = 0; i < 9; i++) {
        mErgebnis += (Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]);
      }
      // Vergleichswert bestimmen
      mRest = mErgebnis % mModulo;
      if (mRest == 1 || mRest == 0) {
        mVergleichswert = 0;
      } else {
        mVergleichswert = mModulo - mRest;
      }
    }
    return mVergleichswert;
  }

  private boolean is_third_a_nine() {
    // Ist die 3. Stelle der mKontonummernummer eine "9"?
    // Aus Doku leider nicht ersichtlich, ob mit 3. Stelle nach der Ergänzung auf
    // 10 Stellen gemeint ist oder vor der Ergänzung. Da nur von "mKontonummernummer" die
    // Rede ist, wird von davor (also der "Original"-mKontonummernummer) ausgegangen
    // Müsste so stimmen nach Testkontonummer
    boolean third_is_a_nine = false;
    if (mKontonummer.charAt(2) == '9') {
      third_is_a_nine = true;
    }
    return third_is_a_nine;
  }
}
