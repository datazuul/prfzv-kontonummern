package com.datazuul.prfzv.kontonummern;

public class PRFZVException extends Exception {

  public PRFZVException(String pMsg) {
    super("<p><b>" + pMsg + "</b></p>");
  }

  public PRFZVException() {
    super("<p><b>Das Prüfzifferverfahren für Ihre Kontonummer konnte nicht instantiitert werden</b></p>");
  }
}
