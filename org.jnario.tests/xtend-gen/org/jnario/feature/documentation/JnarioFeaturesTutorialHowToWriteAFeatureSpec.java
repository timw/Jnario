package org.jnario.feature.documentation;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.jnario.jnario.test.util.FeatureExecutor;
import org.jnario.runner.ExampleGroupRunner;
import org.jnario.runner.Named;
import org.jnario.runner.Order;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * The first example for using Jnario Features is the specification of a calculator. Start out with creating
 * a new file (**File** -> **New** -> **File**) and giving it the file extension _*.feature_.
 * 
 * A feature file consists of the a feature and the scenarios that are the acceptance criteria for the feature.
 * For the calculator the the feature description in form of a story and one scenario for adding
 * numbers looks as follows:
 * 
 * <pre class="prettyprint lang-feature">
 * Feature: Calculator
 * 
 *   As a math dummy
 *   I want a calculator that helps me with basic math operations
 *   In order to check my capabilities
 * 
 *   Scenario: Adding two numbers
 * 
 *     Given a calculator
 *     When I enter two numbers and press add
 *     Then it should show the sum
 * </pre>
 * 
 * A feature consists of the name of the feature, it's narrative and a number of scenarios. The narrative can be arbitrary
 * text. You should use it to describe the rationale of the feature.
 * Those scenarios
 * are examples for the behavior expected from the feature. For the calculator possible
 * scenarios are, for example, addition or subtraction.
 * 
 * Each scenario is defined by the steps that describe the expected behavior. **Given** steps give
 * all the necessary information about the context of the scenario. **When** describes the action
 * and **Then** describes the expected outcome that we want to verify. You can use the keyword **And** to
 * + split a step into different sub steps.
 * 
 * When you save the file an xtend-gen folder will be created and the corresponding generated java files will
 * be created. Those files can be executed as JUnit-Tests. Right click and select **Run As** -> **JUnit Test
 * You will see the result of the run and it will be green. Since the steps from scenario are not implemented
 * yet all the steps will be marked as _PENDING_ as an reminder that there is still work to do.
 */
@SuppressWarnings("all")
@RunWith(ExampleGroupRunner.class)
@Named("How to write a Feature")
public class JnarioFeaturesTutorialHowToWriteAFeatureSpec extends JnarioFeaturesTutorialSpec {
  /**
   * @filter('''|.executesSuccessfully)
   * @lang(feature)
   * 
   * To turn our scenario into an executable specification you have to add the necessary code to each
   * of our steps. For each step the implementation is done directly after the description
   * of the step. The language used to define the logic is [Xtend](http://www.eclipse.org/xtend/documentation/index.html#fields).
   * Create a Java class with the name Calculator in the same project as your feature file.
   * Add the necessary import to the feature file or include it by auto completion while typing (CTRL + SPACE).
   * 
   * <span class="label label-info">Info</span> Specifying the package and declaring and importing other packages
   * works similar to Xtend (static imports work as well).</span>
   * 
   * First we will declare a field `Calculator calculator` which we initialize in our **Given** step (we could
   * also directly initialize the field `Calculator calculator = new Calculator`). We will also create a
   * integer field named `result` to store the result of our calculation.
   * In the **When** step we call `add` on the calculator and pass in the two parameters specified in the step.
   * The return value is assigned to the `result` field.
   * 
   * Parameters can be defined in steps using quotes: `"I am a parameter"`. Within your step implementation,
   * you can access these parameters by an implicitly created variable `args`. Jnario provides some
   * [extension methods](http://www.eclipse.org/xtend/documentation/index.html#extensionMethods) that
   * simplify converting strings to other primitives. Here we use the `toInt` extension to convert the
   * string parameter into an integer.
   * 
   * Now we need to define a method `add` in our calculator to remove the compile errors.
   * In the **Then** step we will assert the behavior of our calculator. For writing readable short assertions
   * different additional assertions come with Jnario. All of the possibilities are
   * described [here](/org/jnario/spec/tests/integration/AssertionSpec.html).
   * Here we use for describing the expected outcome of an expression `=>`.
   * 
   * @filter('''|.executesSuccessfully)
   * @lang(feature)
   */
  @Test
  @Named("Implementing Steps")
  @Order(99)
  public void implementingSteps() throws Exception {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package demo");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.jnario.feature.documentation.Calculator");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Feature: Calculator");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Scenario: Adding two numbers");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("Calculator calculator");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("int result");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("Given a calculator");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("calculator = new Calculator");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("When I add two numbers \"20\" and \"70\"");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("result = calculator.add(args.first.toInt, args.second.toInt)");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("Then it prints \"90\"");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("result => args.first.toInt");
    _builder.newLine();
    FeatureExecutor.executesSuccessfully(_builder);
  }
  
  /**
   * **Given** steps that are used in all scenarios of the same feature can be defined in a `Background`.
   * For example, if we add a second scenario to describe the division of two values, we can move the
   * **Given** step together with it's used fields to a **Background**.
   * All steps defined as background steps will be executed before each steps of a scenario.
   * 
   * @filter('''|.executesSuccessfully)
   * @lang(feature)
   */
  @Test
  @Named("Background")
  @Order(99)
  public void background() throws Exception {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package demo");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import org.jnario.feature.documentation.Calculator");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Feature: Calculator");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Background:");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("Calculator calculator");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("int result");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("Given a calculator");
    _builder.newLine();
    _builder.append("  \t");
    _builder.append("calculator = new Calculator");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Scenario: Adding two numbers");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("When I add two numbers \"20\" and \"70\"");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("result = calculator.add(20,70)");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("Then it prints \"90\"");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("result => args.first.toInt");
    _builder.newLine();
    _builder.append("  ");
    _builder.newLine();
    _builder.append("Scenario: Dividing two numbers");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("When I divide \"70\" by \"10\"");
    _builder.newLine();
    _builder.append(" \t ");
    _builder.append("result = calculator.divide(70,10)");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("Then it prints \"7\"");
    _builder.newLine();
    FeatureExecutor.executesSuccessfully(_builder);
  }
  
  /**
   * Often you want to verify the behavior of a scenario with multiple examples.
   * In case of the calculator you might want to define what happens using negative numbers
   * or large numbers. In this case you can define a table with values that can be used
   * by the scenario steps. Each scenario will be executed separately for each row in the table.
   * 
   * The header of the example table serves as field declaration of the header names. The
   * type is derived from the common super type of all cells in a column.
   * 
   * @filter('''|.executesSuccessfully)
   * @lang(feature)
   */
  @Test
  @Named("Example tables")
  @Order(99)
  public void exampleTables() throws Exception {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package demo");
    _builder.newLine();
    _builder.append("import org.jnario.feature.documentation.Calculator");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Feature: Calculator");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Scenario: Adding two numbers");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("Calculator calculator");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("int result");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("Given a calculator");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("calculator = new Calculator");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("When adding two numbers a and b. ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("result = calculator.add(a, b)");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("Then it returns the result");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("result => expected");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("Examples: additions");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("|  a  |  b  | expected |");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("|  0  |  0  |     0    |");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("| 21  | 21  |    42    |");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("| -3  | -5  |    -8    |");
    _builder.newLine();
    _builder.append("   ");
    _builder.newLine();
    FeatureExecutor.executesSuccessfully(_builder);
  }
  
  /**
   * Once a step is defined including the execution code it can be referenced from
   * other scenarios. The textual description of the step serves as identifier.
   * When you reuse the step in a different scenario and provide no implementation
   * the existing implementation from the referenced step will be used for this step.
   * 
   * Referenced steps are highlighted in grey so you see when you are actually using
   * a referenced step. You can also jump directly to the original step declaration
   * to have a look at the implementation.
   * 
   * @filter('''|.executesSuccessfully)
   * @lang(feature)
   */
  @Test
  @Named("Step references")
  @Order(99)
  public void stepReferences() throws Exception {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package demo");
    _builder.newLine();
    _builder.append("import org.jnario.feature.documentation.Calculator");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Feature: Calculator");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Background:");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("Calculator calculator");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("int result");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("Given a calculator");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("calculator = new Calculator");
    _builder.newLine();
    _builder.newLine();
    _builder.append("Scenario: Adding two numbers");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("When adding two numbers a and b. ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("result = calculator.add(a, b)");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("Then it returns the expected result");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("result => expected");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("Examples:");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("|  a  |  b  | expected |");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("|  0  |  0  |   0    |");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("| -3  | -5  |  -8    |");
    _builder.newLine();
    _builder.append("  ");
    _builder.newLine();
    _builder.append("Scenario: Dividing two numbers");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("When entering two numbers a and b and pressing enter. ");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("result = calculator.divide(a,b)");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("Then it returns the expected result");
    _builder.newLine();
    _builder.append(" ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("Examples:");
    _builder.newLine();
    _builder.append(" ");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("|  a  |  b  | expected |");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("|  8  |  1  |     8    |");
    _builder.newLine();
    _builder.append(" ");
    _builder.newLine();
    FeatureExecutor.executesSuccessfully(_builder);
  }
}