package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

case class KtProperty(
  var name: String = null,
  var expression: String = null,
  var dataType: String = null
) extends ASTNode

object KtProperty extends KotlinParserBaseVisitor[KtProperty] {
  override def visitPropertyDeclaration(
    ctx: KotlinParser.PropertyDeclarationContext
  ): KtProperty =
    new KtProperty(
      name = ctx.variableDeclaration.simpleIdentifier.getText,
      expression = ctx.expression.getText,
      dataType =
        Option(ctx.variableDeclaration.`type`).map(_.getText).getOrElse(null)
    ) {
      context = ctx
    }
}
