/*
 * generated by Xtext
 */
package de.bmw.carit.jnario.scoping;

import static com.google.common.collect.Iterators.filter;
import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.xtext.EcoreUtil2.getContainerOfType;
import static org.eclipse.xtext.scoping.Scopes.scopeFor;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.impl.XFeatureCallImplCustom;
import org.eclipse.xtext.xbase.scoping.LocalVariableScopeContext;
import org.eclipse.xtext.xbase.scoping.XbaseScopeProvider;

import de.bmw.carit.jnario.jnario.And;
import de.bmw.carit.jnario.jnario.Given;
import de.bmw.carit.jnario.jnario.Scenario;
import de.bmw.carit.jnario.jnario.Then;
import de.bmw.carit.jnario.jnario.When;

/**
 * This class contains custom scoping description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#scoping
 * on how and when to use it 
 *
 */
public class JnarioScopeProvider extends XbaseScopeProvider {


	@Override
	protected IScope createLocalVarScope(IScope parentScope,
			LocalVariableScopeContext scopeContext) {
		EObject context = scopeContext.getContext();
		if (context instanceof Given || context instanceof When || context instanceof Then || context instanceof And) {
			return stepScope(parentScope, scopeContext);
		}else if(context instanceof XFeatureCallImplCustom){
			IScope scope = checkIfInExampleScope(parentScope, scopeContext);
			if(scope != null) return scope;
			return super.createLocalVarScope(parentScope, scopeContext);
		}else{
			return super.createLocalVarScope(parentScope, scopeContext);
		}
	}

	private IScope checkIfInExampleScope(IScope parentScope,
			LocalVariableScopeContext scopeContext) {
//		EObject context = scopeContext.getContext();
//		Scenario scenario = getContainerOfType(context, Scenario.class);
//		Examples examples = scenario.getExamples().get(0);
//		for(ExampleCell cell: examples.getHeading().getParts()){
//			if(retrieveExampleVariable(cell.getValue()).equals(context.toString())){
//				return super.createLocalVarScope(parentScope, scopeContext);
//			}
//		}
		return super.createLocalVarScope(parentScope, scopeContext);
	}

	private IScope stepScope(IScope parentScope,
			LocalVariableScopeContext scopeContext) {

		EObject context = scopeContext.getContext();
		Scenario scenario = getContainerOfType(context, Scenario.class);
		Iterator<EObject> eAllContents = scenario.eAllContents();
		Iterator<XVariableDeclaration> allVariables = filter(eAllContents, XVariableDeclaration.class);
		return scopeFor(newArrayList(allVariables));
	}
}