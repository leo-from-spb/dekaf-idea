package org.jetbrains.dekaf.preprocessor;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;


/**
 * @author Leonid Bushuev
 **/
final class QmEntity implements Serializable, Comparable<QmEntity> {

  final int entBegin;
  final int innerBegin;
  final int innerEnd;
  final int entEnd;

  final int entLine;
  final int entPos;
  final int innerLine;
  final int innerPos;

  @NotNull
  final QmEntityType type;


  public QmEntity(int entBegin, int innerBegin, int innerEnd, int entEnd,
                  int entLine, int entPos, int innerLine, int innerPos,
                  @NotNull QmEntityType type) {
    assert entBegin <= innerBegin && innerBegin <= innerEnd && innerEnd <= entEnd;
    this.entBegin = entBegin;
    this.innerBegin = innerBegin;
    this.innerEnd = innerEnd;
    this.entEnd = entEnd;
    this.entLine = entLine;
    this.entPos = entPos;
    this.innerLine = innerLine;
    this.innerPos = innerPos;
    this.type = type;
  }


  @Override
  public int compareTo(@NotNull QmEntity that) {
    int z = this.entBegin - that.entBegin;
    if (z == 0) z = this.innerBegin - that.innerBegin;
    if (z == 0) z = this.innerEnd - that.innerEnd;
    if (z == 0) z = this.entEnd - that.entEnd;
    if (z == 0) z = this.type.ordinal() - that.type.ordinal();
    return z;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    QmEntity that = (QmEntity) o;

    // @formatter:off
    return entBegin   == that.entBegin
        && innerBegin == that.innerBegin
        && innerEnd   == that.innerEnd
        && entEnd     == that.entEnd
        && type       == that.type;
    // @formatter:on
  }


  @Override
  public int hashCode() {
    return entBegin * 29 + innerBegin * 17 + innerEnd * 7 + entEnd * 3;
  }
}
