package org.jetbrains.dekaf.preprocessor;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;



/**
 * Interval in the text.
 *
 * @author Leonid Bushuev
 **/
public final class TextInterval implements Serializable {

  /**
   * The begin of the interval (inclusive).
   */
  @NotNull
  public final TextPosition beg;


  /**
   * The end of the interval (exclusive).
   * Points to the character that follows this interval.
   */
  @NotNull
  public final TextPosition end;


  /**
   * Trivial constructor.
   * @param beg  begin of the interval (inclusive).
   * @param end  end of the interval (exclusive).
   */
  public TextInterval(@NotNull final TextPosition beg, @NotNull final TextPosition end) {
    this.beg = beg;
    this.end = end;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TextInterval)) return false;

    TextInterval that = (TextInterval) o;

    return this.beg.equals(that.beg)
        && this.end.equals(that.end);
  }


  @Override
  public int hashCode() {
    return beg.offset;
  }


  @Override
  public String toString() {
    return beg.toString() + "-" + end.toString();
  }
}
