package org.jetbrains.dekaf.preprocessor;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;


/**
 * @author Leonid Bushuev
 **/
final class QmEntity implements Serializable, Comparable<QmEntity> {

  /**
   * Whole entity room in the text.
   */
  @NotNull
  final TextInterval room;

  /**
   * The content part interval in the text.
   * If no content, may be the same as the {@see #room}.
   */
  @NotNull
  final TextInterval content;

  /**
   * Type of the entity.
   */
  @NotNull
  final QmEntityType type;


  public QmEntity(@NotNull TextInterval room, @NotNull TextInterval content, @NotNull QmEntityType type) {
    this.room = room;
    this.content = content;
    this.type = type;
  }


  @Override
  public int compareTo(@NotNull QmEntity that) {
    int z = this.room.beg.compareTo(that.room.beg);
    if (z == 0) z = this.content.beg.compareTo(that.content.beg);
    if (z == 0) z = this.content.end.compareTo(that.content.end);
    if (z == 0) z = this.room.end.compareTo(that.room.end);
    if (z == 0) z = this.type.ordinal() - that.type.ordinal();
    return z;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    QmEntity that = (QmEntity) o;

    // @formatter:off
    return room.equals(that.room)
        && content.equals(that.content)
        && type == that.type;
    // @formatter:on
  }


  @Override
  public int hashCode() {
    return room.hashCode() * 37 + content.hashCode() * 7;
  }


  @Override
  public String toString() {
    return room + " | " + content + " | " + type;
  }
}
