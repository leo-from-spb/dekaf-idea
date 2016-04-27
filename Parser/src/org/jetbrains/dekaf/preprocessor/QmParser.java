package org.jetbrains.dekaf.preprocessor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import static java.lang.Character.*;
import static org.jetbrains.dekaf.preprocessor.QmEntityType.*;
import static org.jetbrains.dekaf.preprocessor.QmEntityType.ERROR;



/**
 * @author Leonid Bushuev
 **/
final class QmParser {

  private static final char EOT = '\0';

  @NotNull
  private final QmOptions options;

  @NotNull
  private CharSequence text;

  private int len;

  private int offset = 0,
              line = 1,
              pos = 1;

  private char cp, cc, cn;   // c-prior, c-current, c-next
  private boolean lb;        // line begin
  private boolean eof;

  private final ArrayList<QmEntity> entities;

  private QmEntityBuilder eb = new QmEntityBuilder();


  public QmParser(@NotNull CharSequence text) {
    this(text, QmOptions.DEFAULT);
  }


  public QmParser(@NotNull CharSequence text, @NotNull QmOptions options) {
    this.options = options;
    this.text = text;
    len = text.length();
    cc = len > 0 ? text.charAt(0) : EOT;
    cn = len > 1 ? text.charAt(1) : EOT;
    cp = '\0';
    lb = true;
    entities = new ArrayList<QmEntity>(len / 64);
  }



  @NotNull
  public QmEntity[] parse() {
    while (!eof) {
      skipSpace();
      parseEntity();
    }

    eb.reset(offset, line, pos);
    eb.setType(EOF);
    push();

    return entities.toArray(new QmEntity[0]);
  }


  private void parseEntity() {
    eb.reset(offset, line, pos);
    boolean done = eof;
    switch (cc) {
      case '-':
        if (!done && cn == '-') done = parseSectionHeader();
        if (!done && cn == '-') done = parseLineComment();
        break;
      case '/':
        if (!done && cn == '*') done = parseBlockComment();
        if (!done && lb) done = parseStatementEndMark();
        break;
      case ';':
        if (!done && lb) done = parseStatementEndMark();
        break;
      case '#':
        if (!done && cn == '[') done = parseEnclosedMacro();
        if (!done && cn == ':') done = parseOtherwiseConditionMark();
        if (!done && cn == '.') done = parseEndConditionMark();
        if (!done && isUpperCase(cn) && options.shortMacro) done = parseShortMacro();
        break;
      case '\'':
        done = parseString();
        break;
      case '"':
        done = parseQuotedIdentifier();
        break;
      case '[':
        done = parseBracketIdentifier();
        break;
      case ':':
        if (!done && cp != ':' && isUnicodeIdentifierStart(cn)) done = parseNamedParameter();
        if (!done && cp != ':' && cn == '[') done = parseComplexParameter();
        break;
      case '?':
        if (!done && cp != '?' && cn != '?') done = parseSimpleParameter();
        break;
    }

    if (!done) done = parseSqlStuff();
    if (!done) done = parseError();
    assert done : "Internal parsing error";
  }


  private boolean parseSectionHeader() {
    // TODO implement QmParser.parseSectionHeader()
    throw new RuntimeException("Method QmParser.parseSectionHeader() is not implemented yet.");

  }


  private boolean parseLineComment() {
    // TODO implement QmParser.parseLineComment()
    throw new RuntimeException("Method QmParser.parseLineComment() is not implemented yet.");

  }


  private boolean parseBlockComment() {
    // TODO implement QmParser.parseBlockComment()
    throw new RuntimeException("Method QmParser.parseBlockComment() is not implemented yet.");

  }


  private boolean parseEnclosedMacro() {
    // TODO implement QmParser.parseEnclosedMacro()
    throw new RuntimeException("Method QmParser.parseEnclosedMacro() is not implemented yet.");

  }


  private boolean parseOtherwiseConditionMark() {
    // TODO implement QmParser.parseOtherwiseConditionMark()
    throw new RuntimeException("Method QmParser.parseOtherwiseConditionMark() is not implemented yet.");

  }


  private boolean parseEndConditionMark() {
    // TODO implement QmParser.parseEndConditionMark()
    throw new RuntimeException("Method QmParser.parseEndConditionMark() is not implemented yet.");

  }


  private boolean parseShortMacro() {
    // TODO implement QmParser.parseShortMacro()
    throw new RuntimeException("Method QmParser.parseShortMacro() is not implemented yet.");

  }


  private boolean parseString() {
    // TODO implement QmParser.parseString()
    throw new RuntimeException("Method QmParser.parseString() is not implemented yet.");

  }


  private boolean parseQuotedIdentifier() {
    // TODO implement QmParser.parseQuotedIdentifier()
    throw new RuntimeException("Method QmParser.parseQuotedIdentifier() is not implemented yet.");

  }


  private boolean parseBracketIdentifier() {
    // TODO implement QmParser.parseBracketIdentifier()
    throw new RuntimeException("Method QmParser.parseBracketIdentifier() is not implemented yet.");

  }


  private boolean parseSimpleParameter() {
    // TODO implement QmParser.parseSimpleParameter()
    throw new RuntimeException("Method QmParser.parseSimpleParameter() is not implemented yet.");

  }


  private boolean parseNamedParameter() {
    // TODO implement QmParser.parseNamedParameter()
    throw new RuntimeException("Method QmParser.parseNamedParameter() is not implemented yet.");

  }


  private boolean parseComplexParameter() {
    // TODO implement QmParser.parseComplexParameter()
    throw new RuntimeException("Method QmParser.parseComplexParameter() is not implemented yet.");

  }


  private boolean parseSqlStuff() {
    loop:
    while (!eof) {
      nextChar();
      switch (cc) {
        case '-':
          if (cn == '-') break loop;
          break;
        case '/':
        case ';':
          if (lb) break loop;
          break;
        case '#':
          if (cn == '[' || cn == ':' || cn == '.') break loop;
          if (isUpperCase(cn) && options.shortMacro) break loop;
          break;
        case '\'':
        case '"':
        case '[':
          break loop;
        case ':':
          if (cp != ':' && isUnicodeIdentifierStart(cn)) break loop;
          if (cp != ':' && cn == '[') break loop;
          break;
        case '?':
          if (cp != '?' && cn != '?') break loop;
          break;
      }
    }

    if (offset > eb.entBegin) {
      eb.setType(SQL_STUFF);
      eb.setInnerEnd(offset);
      eb.setEntEnd(offset);
      push();
      return true;
    }
    else {
      return false;
    }
  }


  private boolean parseError() {
    loop:
    while (!eof) {
      nextChar();
      switch (cc) {
        case '-':
          if (cn == '-') break loop;
          break;
        case '/':
        case ';':
          if (lb) break loop;
          break;
        case '#':
          if (cn == '[' || cn == ':' || cn == '.') break loop;
          if (isUpperCase(cn) && options.shortMacro) break loop;
          break;
        case '\'':
        case '"':
        case '[':
          break loop;
        case ':':
          if (cp != ':' && isUnicodeIdentifierStart(cn)) break loop;
          if (cp != ':' && cn == '[') break loop;
          break;
        case '?':
          if (cp != '?' && cn != '?') break loop;
          break;
      }
    }

    if (offset > eb.entBegin) {
      eb.setType(ERROR);
      eb.setInnerEnd(offset);
      eb.setEntEnd(offset);
      push();
      return true;
    }
    else {
      return false;
    }
  }


  private boolean parseStatementEndMark() {
    // TODO implement QmParser.parseStatementEndMark()
    throw new RuntimeException("Method QmParser.parseStatementEndMark() is not implemented yet.");

  }




  private void skipSpace() {
    while (isWhitespace(cc)) nextChar();
  }

  private void nextChar() {
    offset++;
    pos++;
    eof = offset >= len;
    cp = cc;
    cc = cn;
    cn = offset+1 < len ? text.charAt(offset+1) : EOT;
    if (cp == '\n' || cp == '\r' && cc != '\n') {
      line++;
      pos = 0;
      lb = true;
    }
    else if (lb) {
      if (!isWhitespace(cp)) lb = false;
    }
  }


  @Nullable
  private QmEntity lastEntity() {
    int n = entities.size();
    return n > 0 ? entities.get(n-1) : null;
  }


  private void push() {
    QmEntity entity = eb.build();
    entities.add(entity);
  }


}
