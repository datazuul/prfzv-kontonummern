package com.datazuul.prfzv.kontonummern;

/**
 * Pruefverfahren 09
 *
 * <p>
 * Keine Prüfzifferberechnung</p>
 */
public class PV09 extends PRFZV {

  public PV09() {
  }

  @Override
  public int getVergleichswert() {
    // Laut Vorgabe erfolgt bei 09 keine Prüfzifferberechnung. Daher Rückgabe der Prüfziffer => true
    return getPruefziffer();
  }
}
