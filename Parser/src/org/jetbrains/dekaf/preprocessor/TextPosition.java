package org.jetbrains.dekaf.preprocessor;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;



/**
 * Position in the text.
 *
 * @author Leonid Bushuev
 **/
public final class TextPosition implements Serializable, Comparable<TextPosition> {

  /**
   * The line number. Starts with 1.
   */
  public final int line;

  /**
   * The position of the symbol in the line. Starts with 1.
   */
  public final int pos;

  /**
   * The offset from the begin of the text.
   * Starts with 0.
   */
  public final int offset;


  /**
   * Trivial constructor.
   * @param line    the line number. Starts with 1.
   * @param pos     the position of the symbol in the line. Starts with 1.
   * @param offset  the offset from the begin of the text. Starts with 0.
   */
  public TextPosition(int line, int pos, int offset) {
    this.line = line;
    this.pos = pos;
    this.offset = offset;
  }


  @Override
  public int compareTo(@NotNull final TextPosition that) {
    int z = this.line - that.line;
    if (z == 0) z = this.pos - that.pos;
    if (z == 0) z = this.offset - that.offset;
    return z;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TextPosition)) return false;

    TextPosition that = (TextPosition) o;

    return this.line == that.line
        && this.pos == that.pos
        && this.offset == that.offset;
  }


  @Override
  public int hashCode() {
    return offset;
  }


  @Override
  public String toString() {
    return Integer.toString(line) + ':' + pos + ':' + offset;
  }

}
