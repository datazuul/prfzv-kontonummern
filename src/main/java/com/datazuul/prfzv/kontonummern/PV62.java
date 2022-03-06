package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 62
 *
 * <p>
 * Modulus 10, Gewichtung 2, 1, 2, 1, 2</p>
 *
 * <p>
 * Die beiden ersten und die beiden letzten Stellen sind nicht zu berücksichtigen. Die Stellen drei bis sieben sind von rechts nach links mit den Ziffern 2, 1, 2, 1, 2 zu multiplizieren. Aus
 * zweistelligen Einzelergebnissen ist eine Quersumme zu bilden. Alle Ergebnisse sind dann zu addieren. Die Differenz zum nächsten Zehner ergibt die Prüfziffer auf Stelle acht. Ist die Differenz 10,
 * ist die Prüfziffer 0.</p>
 *
 * <pre>
 * Stellennr.: 1 2 3 4 5 6 7 8 9 A (A = 10)
 * Kontonr.:   5 0 2 9 0 7 6 P 0 1
 * Gewichtung:     2 1 2 1 2
 *                 4 + 9 + 0 + 7 + 3 = 23
 *                                (Q)         (Q = Quersumme)
 * </pre>
 *
 * <p>
 * Die Einerstelle wird vom Wert 10 subtrahiert 10 - 3 = 7.</p>
 *
 * <p>
 * Die Prüfziffer ist in diesem Fall die 7 und die vollständige Kontonummer lautet: 5 0 2 9 0 7 6 7 0 1 </p>
 */
public class PV62 extends PRFZV {

  public PV62() {
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
    int quersumme = 0;

    for (int i = 2; i < 7; ++i) {
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
