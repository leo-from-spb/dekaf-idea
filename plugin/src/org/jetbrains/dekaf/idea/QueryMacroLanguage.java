package org.jetbrains.dekaf.idea;

import com.intellij.lang.Language;



/**
 * @author Leonid Bushuev
 **/
public final class QueryMacroLanguage extends Language {

  /**
   * The QM Language instance.
   */
  public static final QueryMacroLanguage INSTANCE = new QueryMacroLanguage();


  private QueryMacroLanguage() {
    super("QM");
  }



}
