package org.jetbrains.dekaf.preprocessor;

import org.jetbrains.annotations.NotNull;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;



@FixMethodOrder(MethodSorters.JVM)
public class QmParserTest {

  @Test
  public void basic() {
    String text =
        "A B C\n" +
        "D E F\n" +
        "G H I\n";
    QmParser parser = new QmParser(text);
    QmEntity[] entities = parser.parse();
    String dump = dump(entities);

    assertThat(dump).contains("1:1:0-4:0:18 | 1:1:0-4:0:18 | SQL_STUFF")
                    .contains("4:0:18-4:0:18 | 4:0:18-4:0:18 | EOF");
  }


  @NotNull
  private String dump(QmEntity[] entities) {
    if (entities == null) return "(null)";
    return dump(Arrays.asList(entities));
  }


  @NotNull
  private static String dump(final Iterable<QmEntity> entities) {
    if (entities == null) return "(null)";
    StringBuilder b = new StringBuilder();
    for (QmEntity entity : entities) b.append(entity.toString()).append('\n');
    return b.toString();
  }

}