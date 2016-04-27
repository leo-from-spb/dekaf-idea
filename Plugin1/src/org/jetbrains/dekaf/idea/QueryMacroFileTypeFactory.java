package org.jetbrains.dekaf.idea;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;



/**
 * @author Leonid Bushuev
 **/
public class QueryMacroFileTypeFactory extends FileTypeFactory {


  @Override
  public void createFileTypes(@NotNull FileTypeConsumer consumer) {
    consumer.consume(QueryMacroFileType.INSTANCE, QueryMacroFileType.FILE_SUFFIX);
  }

}
