package com.datazuul.prfzv.kontonummern;

public class PRFZVFactory {

  public static PRFZV createInstance(String pPRFZV_ID)
          throws PRFZVException {
    Object obj = null;
    try {
      obj = Class.forName("com.datazuul.prfzv.kontonummern.PV" + pPRFZV_ID.trim()).newInstance();
    } catch (Exception e) {
      throw new PRFZVException(e.getMessage());
    }
    if (!(obj instanceof PRFZV)) {
      throw new PRFZVException("Prüfzifferverfahren nicht gefunden");
    }
    return (PRFZV) obj;

  }

  public static void main(String[] argv) {
    try {
      PRFZV prfzv = PRFZVFactory.createInstance(argv[0]);
      prfzv.check(argv[1]);
    } catch (Exception e) {
      System.err.println();
    }
  }
}
