package org.jetbrains.dekaf.preprocessor;

import org.jetbrains.annotations.NotNull;



/**
 * @author Leonid Bushuev
 **/
@SuppressWarnings("WeakerAccess")
class QmEntityBuilder {

  int entBegin;
  int innerBegin;
  int innerEnd;
  int entEnd;

  int entLine;
  int entPos;
  int innerLine;
  int innerPos;

  @NotNull
  private QmEntityType type = QmEntityType.SPACE;


  public QmEntityBuilder() {
    entLine = entPos = innerLine = innerPos = 1;
  }


  public QmEntityBuilder(int entBegin, int entLine, int entPos, @NotNull QmEntityType type) {
    reset(entBegin, entLine, entPos);
    this.type = type;
  }


  public QmEntityBuilder(QmEntity from) {
    // @formatter:off
    this.entBegin   = from.entBegin  ;
    this.innerBegin = from.innerBegin;
    this.innerEnd   = from.innerEnd  ;
    this.entEnd     = from.entEnd    ;
    this.entLine    = from.entLine   ;
    this.entPos     = from.entPos    ;
    this.innerLine  = from.innerLine ;
    this.innerPos   = from.innerPos  ;
    this.type       = from.type      ;
    // @formatter:on
  }


  public void reset(int entBegin, int entLine, int entPos) {
    this.innerBegin = this.entBegin = entBegin;
    this.innerLine  = this.entLine  = entLine;
    this.innerPos   = this.entPos   = entPos;
    this.innerEnd   = this.entEnd   = entBegin;
    this.type = QmEntityType.SPACE;
  }


  public void setInnerBegin(int innerBegin, int innerLine, int innerPos) {
    this.innerBegin = innerBegin;
    this.innerLine  = innerLine;
    this.innerPos   = innerPos;
  }

  public void setInnerEnd(int innerEnd) {
    this.innerEnd = innerEnd;
  }


  public void setEntEnd(int entEnd) {
    this.entEnd = entEnd;
  }


  public void setType(@NotNull QmEntityType type) {
    this.type = type;
  }


  @NotNull
  public QmEntity build() {
    return new QmEntity(entBegin,
                        innerBegin,
                        innerEnd,
                        entEnd,
                        entLine,
                        entPos,
                        innerLine,
                        innerPos,
                        type);
  }
}
