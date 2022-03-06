package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 66 (geändert zum 03.03.2014)
 *
 * <p>
 * Modulus 11, Gewichtung 2, 3, 4, 5, 6, 0, 0, 7</p>
 *
 * <p>
 * Aufbau der 9-stelligen Kontonummer (innerhalb des zwischenbetrieblich 10-stelligen Feldes)</p>
 *
 * <pre>
 * Stelle
 * 1 = gehört nicht zur Kontonummer, muss daher 0 sein
 * 2 = Stammnummer
 * 3 - 4 = Unterkontonummer, wird bei der Prüfzifferberechnung nicht berücksichtigt
 * 5 - 9 = Stammnummer
 * 10 = Prüfziffer
 * </pre>
 *
 * <p>
 * Der 9-stelligen Kontonummer wird für die Prüfzifferberechnung eine 0 vorangestellt. Die Prüfziffer steht in Stelle 10. Die für die Berechnung relevante 6-stellige Stammnummer (Kundenummer) befindet
 * sich in den Stellen 2 und 5 bis 9. Die zweistellige Unterkontonummer (Stellen 3 und 4) wird nicht in das Prüfzifferberechnungsverfahren mit einbezogen und daher mit 0 gewichtet. Die einzelnen
 * Stellen der Stammnummer sind von rechts nach links mit den Ziffern 2, 3, 4, 5, 6, 0, 0, 7 zu multiplizieren. Die jeweiligen Produkte werden addiert. Die Summe ist durch 11 zu dividieren. Bei einem
 * verbleibenden Rest von 0 ist die Prüfziffer 1. Bei einem Rest von 1 ist die Prüfziffer 0. Verbleibt ein Rest von 2 bis 10, so wird dieser vom Divisor (11) subtrahiert. Die Differenz ist dann die
 * Prüfziffer.</p>
 *
 * <pre>
 * Zusammengefasst:
 * Summe dividiert durch 11 = x, Rest
 *
 * Rest = 0 Prüfziffer = 1
 * Rest = 1 Prüfziffer = 0
 * Rest = 2 bis 10 Prüfziffer = 11 minus Rest
 *
 * Stellennr.: 1 2 3 4 5 6 7 8 9 10
 * Kontonr.:   0 1 0 0 1 5 0 5 0 P
 * Gewichtung: 0 7 0 0 6 5 4 3 2
 *             0 + 7 + 0 + 0 + 6 +25 + 0 +15 + 0 = 53
 *             53 : 11 = 4, Rest 9, 11 - 9 = 2, Prüfziffer = 2
 * </pre>
 *
 * <p>
 * Die vollständige Kontonummer lautet: 100150502</p>
 *
 * <p>
 * Ausnahme:<br>
 * Ist die Stelle 2 der Kontonummer der Wert = 9, ist die Kontonummer nicht prüfziffergesichert; es gilt die Methode 09 (keine Prüfzifferberechnung). Beispiel der Kontonummer: 0983393104.</p>
 *
 * <p>
 * Testkontonummern: 100154508, 101154508, 100154516, 101154516 </p>
 */
public class PV66 extends PRFZV {

  public PV66() {
    // vorgegebene mGewichtung
    mGewichtungswerte = new int[]{2, 3, 4, 5, 6};

    // vorgegebener mModulo-Wert
    mModulo = 11;

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
    Integer zwischenmErgebnis;
    String zwischenmErgebnisstring;
    int quersumme;

    for (int i = 4; i < 9; ++i) {
      // Multiplikation der mGewichtung mit mKontonummer
      zwischenmErgebnis = (Integer.parseInt(mKontonummer.substring(i, i + 1)) * mGewichtung[i]);
      // Quersumme der MultiplikationsmErgebnisse bilden
      zwischenmErgebnisstring = zwischenmErgebnis.toString();
      quersumme = zwischenmErgebnis;
      // Quersummen addieren
      mErgebnis += quersumme;
    }
    mErgebnis = mErgebnis % mModulo;
    // Vergleichswert bestimmen
    mErgebnis = mModulo - mErgebnis;
    if (mErgebnis == 0) {
      mErgebnis = 1;
    }
    if (mErgebnis == 1) {
      mErgebnis = 0;
    }
    return mErgebnis;
  }
}
