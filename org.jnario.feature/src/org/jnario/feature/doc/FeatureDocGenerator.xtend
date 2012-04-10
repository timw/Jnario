package org.jnario.feature.doc

import com.google.inject.Inject
import org.eclipse.xtend.core.xtend.RichString
import org.eclipse.xtend.core.xtend.XtendClass
import org.jnario.doc.AbstractDocGenerator
import org.jnario.feature.feature.Feature
import org.jnario.feature.feature.Scenario
import org.jnario.feature.feature.Step
import org.jnario.feature.naming.JavaNameProvider
import org.jnario.feature.naming.StepNameProvider
import static extension org.jnario.util.Strings.*
import static org.jnario.doc.HtmlFile.*

class FeatureDocGenerator extends AbstractDocGenerator {
	@Inject extension JavaNameProvider 
	@Inject extension StepNameProvider 

	override createHtmlFile(XtendClass xtendClass) {
		if(!(xtendClass instanceof Feature)){
			return EMPTY_FILE
		}
		val feature = xtendClass as Feature
		newHtmlFile[
			fileName = feature.className 
			title = feature.name
			content = feature.generateContent
			rootFolder = feature.root
		]
	}
	
	def generateContent(Feature feature)'''
		<p>�feature.description.markdown2Html�</p>
		�IF feature.background != null�
		<h3>Background</h3>
		�FOR step : feature.background.steps.filter(typeof(Step))�
		�generate(step)�
		�ENDFOR�
		�ENDIF�
		�FOR scenario : feature.members.filter(typeof(Scenario))�
		�generate(scenario)�
		�ENDFOR�
	'''

	def generate(Scenario scenario)'''
		<h3>�scenario.name�</h3>
		�generate(scenario.steps.filter(typeof(Step)))�
		�IF !scenario.examples.empty�
		<h4>Examples:</h4>
		�FOR example : scenario.examples�
		<p>�generate(example)�</p>
		�ENDFOR�
		�ENDIF�
	'''
	
	def generate(Iterable<Step> steps)'''
		<ul>
		�FOR step : steps�
		<li>�generate(step)�
		�generate(step.and.filter(typeof(Step)))�</li>
		�ENDFOR�
		</ul>
	'''
	
	def generate(Step step)'''
		<p>�step.format�</p>
		�step.addCodeBlock�
	'''

	def format(Step step){
		var result = step.nameOf
		result = result.replaceFirst("(" + result.firstWord + ")", "**$1**")
		result = result.replaceAll("\"(.*)\"", "<code>$1</code>")
		result.markdown2Html
	}

	def CharSequence addCodeBlock(Step step){
		val expressions = step.stepExpression?.blockExpression?.expressions
		if(expressions == null || expressions.empty) return ""
		val firstExpr = expressions.get(0)
		if(!(firstExpr instanceof RichString)){
			return ""
		} 
		val richString = firstExpr as RichString
		'''<pre>�richString.serialize.replace("'''", "").codeToHtml�</pre>
		'''
	} 
}