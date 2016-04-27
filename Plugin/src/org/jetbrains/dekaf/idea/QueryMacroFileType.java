package org.jetbrains.dekaf.idea;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;



/**
 * @author Leonid Bushuev
 **/
public class QueryMacroFileType extends LanguageFileType {

  public final static QueryMacroFileType INSTANCE = new QueryMacroFileType();
  public static final String FILE_SUFFIX = "qm";


  private QueryMacroFileType() {
    super(QueryMacroLanguage.INSTANCE);
  }


  @NotNull
  @Override
  public String getName() {
    return "QM";
  }


  @NotNull
  @Override
  public String getDescription() {
    return "SQL with Macros";
  }


  @NotNull
  @Override
  public String getDefaultExtension() {
    return FILE_SUFFIX;
  }


  @Nullable
  @Override
  public Icon getIcon() {
    return QueryMacroIcons.FILE_ICON;
  }


}
