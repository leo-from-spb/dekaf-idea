package org.jetbrains.dekaf.preprocessor;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jetbrains.dekaf.preprocessor.QmEntityType.EOF;
import static org.jetbrains.dekaf.preprocessor.QmEntityType.SQL_STUFF;



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

    assertThat(entities.length).isEqualTo(2); // 1 - our SQL text, 2 - EOF

    QmEntity entity = entities[0];

    assertThat(entity.entBegin).isEqualTo(0);
    assertThat(entity.entLine).isEqualTo(1);
    assertThat(entity.entPos).isEqualTo(1);
    assertThat(entity.innerBegin).isEqualTo(0);
    assertThat(entity.innerLine).isEqualTo(1);
    assertThat(entity.innerPos).isEqualTo(1);

    assertThat(entity.entEnd).isEqualTo(18);
    assertThat(entity.innerEnd).isEqualTo(18);

    assertThat(entity.type).isEqualTo(SQL_STUFF);

    QmEntity eof = entities[1];

    assertThat(eof.entBegin).isEqualTo(18);
    assertThat(eof.entEnd).isEqualTo(18);
    assertThat(eof.innerBegin).isEqualTo(18);
    assertThat(eof.innerEnd).isEqualTo(18);

    assertThat(eof.type).isEqualTo(EOF);
  }


}