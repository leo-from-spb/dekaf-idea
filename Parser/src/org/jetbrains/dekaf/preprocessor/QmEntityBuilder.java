package org.jetbrains.dekaf.preprocessor;

import org.jetbrains.annotations.NotNull;



/**
 * @author Leonid Bushuev
 **/
@SuppressWarnings("WeakerAccess")
class QmEntityBuilder {

  @NotNull
  TextPosition roomBeg;

  @NotNull
  TextPosition roomEnd;

  @NotNull
  TextPosition contentBeg;

  @NotNull
  TextPosition contentEnd;

  @NotNull
  private QmEntityType type;


  public QmEntityBuilder() {
    roomBeg = roomEnd = contentBeg = contentEnd = VERY_BEGINNING;
    type = QmEntityType.SPACE;
  }


  public void reset(@NotNull final TextPosition position) {
    roomBeg = roomEnd = contentBeg = contentEnd = position;
    type = QmEntityType.SPACE;
  }


  public void setRoomBeg(@NotNull TextPosition roomBeg) {
    this.roomBeg = roomBeg;
  }


  public void setRoomEnd(@NotNull TextPosition roomEnd) {
    this.roomEnd = roomEnd;
  }


  public void setContentBeg(@NotNull TextPosition contentBeg) {
    this.contentBeg = contentBeg;
  }


  public void setContentEnd(@NotNull TextPosition contentEnd) {
    this.contentEnd = contentEnd;
  }


  public void setType(@NotNull QmEntityType type) {
    this.type = type;
  }


  @NotNull
  public QmEntity build() {
    return new QmEntity(new TextInterval(roomBeg, roomEnd),
                        new TextInterval(contentBeg, contentEnd),
                        type);
  }


  private static final TextPosition VERY_BEGINNING = new TextPosition(1,1,0);
}
