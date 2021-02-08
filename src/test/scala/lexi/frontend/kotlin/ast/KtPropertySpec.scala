package lexi.frontend.kotlin.ast

import lexi.frontend.kotlin.phases.SyntaxAnalysis

class KtPropertySpec extends munit.FunSuite {
  private def ktProperty(ast: ASTNode): KtProperty =
    ast
      .asInstanceOf[KtFile]
      .topLevelObjects
      .get
      .head
      .declaration
      .get
      .propertyDeclaration
      .get

  test("integer declaration") {
    val source = """val x: Int = 5"""
    val ast = SyntaxAnalysis(source)
    val property = ktProperty(ast)
    val expected = KtProperty(
      name = Some("x"),
      expression = Some("5"),
      dataType = Some("Int")
    )
    assertEquals(property, expected)
  }

  test("string declaration") {
    val ast = SyntaxAnalysis("""val firstName: String = "Matt"""")
    val property = ktProperty(ast)
    val expected = KtProperty(
      name = Some("firstName"),
      expression = Some(""""Matt""""),
      dataType = Some("String")
    )
    assertEquals(property, expected)
  }

  test("empty string declaration") {
    val ast = SyntaxAnalysis("""val firstName: String = """"")
    val property = ktProperty(ast)
    val expected = KtProperty(
      name = Some("firstName"),
      expression = Some("\"\""),
      dataType = Some("String")
    )
    assertEquals(property, expected)
  }
}
