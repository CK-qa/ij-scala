package org.jetbrains.plugins.scala
package lang
package parser
package parsing
package types

import org.jetbrains.plugins.scala.lang.lexer.ScalaTokenTypes
import org.jetbrains.plugins.scala.lang.parser.parsing.builder.ScalaPsiBuilder
import org.jetbrains.plugins.scala.lang.parser.util.ParserUtils

/**
* @author Alexander Podkhalyuzin
* Date: 08.02.2008
*/

/*
 * SelfType ::= id [':' Type] '=>' |
 *              ['this' | '_'] ':' Type '=>'
 */
object SelfType {

  def parse(builder: ScalaPsiBuilder): Unit = {
    val selfTypeMarker = builder.mark
    
    def handleFunArrow(): Unit = {
      builder.advanceLexer() //Ate '=>'
      selfTypeMarker.done(ScalaElementType.SELF_TYPE)
    }
    
    def handleColon(): Unit = {
      builder.advanceLexer() //Ate ':'
      
      if (!parseType(builder)) selfTypeMarker.rollbackTo()
        else {
          builder.getTokenType match {
            case ScalaTokenTypes.tFUNTYPE => handleFunArrow()
            case _ => selfTypeMarker.rollbackTo()
          }
        }
    }
    
    def handleLastPart(): Unit = {
      builder.getTokenType match {
        case ScalaTokenTypes.tCOLON => handleColon()
        case ScalaTokenTypes.tFUNTYPE => handleFunArrow()
        case _ => selfTypeMarker.rollbackTo()
      }
    }
    
    builder.getTokenType match {
      case ScalaTokenTypes.kTHIS | ScalaTokenTypes.tUNDER =>
        builder.advanceLexer() // Ate this or _
        builder.getTokenType match {
          case ScalaTokenTypes.tCOLON => handleColon()
          case _ => selfTypeMarker.rollbackTo()
        }
      case ScalaTokenTypes.tIDENTIFIER =>
        builder.advanceLexer() //Ate identifier
        handleLastPart()
      case ScalaTokenTypes.tLPARENTHESIS => 
         if (ParserUtils.parseBalancedParenthesis(builder, TokenSets.SELF_TYPE_ID))
           handleLastPart() else selfTypeMarker.rollbackTo()
      case _ => selfTypeMarker.rollbackTo()
    }
  }

  def parseType(builder : ScalaPsiBuilder) : Boolean = {
    val typeMarker = builder.mark
    if (!InfixType.parse(builder, star = false, isPattern = true)) {
      typeMarker.drop()
      return false
    }

    builder.getTokenType match {
      case ScalaTokenTypes.kFOR_SOME =>
        ExistentialClause parse builder
        typeMarker.done(ScalaElementType.EXISTENTIAL_TYPE)
      case _ => typeMarker.drop()
    }
    true
  }
}