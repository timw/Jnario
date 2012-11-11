package org.jnario.feature.jvmmodel;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.core.xtend.XtendClass;
import org.eclipse.xtend.core.xtend.XtendField;
import org.eclipse.xtend.core.xtend.XtendMember;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.common.types.JvmAnnotationReference;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.XbaseFactory;
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor.IPostIndexingInitializing;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociator;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.jnario.feature.feature.Background;
import org.jnario.feature.feature.Feature;
import org.jnario.feature.feature.FeatureFile;
import org.jnario.feature.feature.Scenario;
import org.jnario.feature.feature.Step;
import org.jnario.feature.feature.StepExpression;
import org.jnario.feature.feature.StepImplementation;
import org.jnario.feature.feature.StepReference;
import org.jnario.feature.jvmmodel.JvmFieldReferenceUpdater;
import org.jnario.feature.jvmmodel.Scenarios;
import org.jnario.feature.jvmmodel.StepArgumentsProvider;
import org.jnario.feature.jvmmodel.StepExpressionProvider;
import org.jnario.feature.jvmmodel.StepReferenceFieldCreator;
import org.jnario.feature.naming.FeatureClassNameProvider;
import org.jnario.feature.naming.StepNameProvider;
import org.jnario.jvmmodel.ExtendedJvmTypesBuilder;
import org.jnario.jvmmodel.JnarioJvmModelInferrer;
import org.jnario.jvmmodel.SpecJvmModelProcessor;
import org.jnario.lib.StepArguments;
import org.jnario.runner.Named;
import org.jnario.runner.Order;

/**
 * @author Birgit Engelmann - Initial contribution and API
 * @author Sebastian Benz
 */
@SuppressWarnings("all")
public class FeatureJvmModelInferrer extends JnarioJvmModelInferrer {
  public final static String STEP_VALUES = "args";
  
  @Inject
  private ExtendedJvmTypesBuilder _extendedJvmTypesBuilder;
  
  @Inject
  private TypeReferences _typeReferences;
  
  @Inject
  private FeatureClassNameProvider _featureClassNameProvider;
  
  @Inject
  private StepNameProvider _stepNameProvider;
  
  @Inject
  private StepExpressionProvider _stepExpressionProvider;
  
  @Inject
  private StepReferenceFieldCreator _stepReferenceFieldCreator;
  
  @Inject
  private StepArgumentsProvider stepArgumentsProvider;
  
  @Inject
  private IJvmModelAssociator _iJvmModelAssociator;
  
  @Inject
  private IJvmModelAssociations _iJvmModelAssociations;
  
  @Inject
  private JvmFieldReferenceUpdater _jvmFieldReferenceUpdater;
  
  public void doInfer(final EObject object, final IJvmDeclaredTypeAcceptor acceptor, final boolean preIndexingPhase) {
    final Feature feature = this.resolveFeature(object);
    boolean _or = false;
    boolean _equals = Objects.equal(feature, null);
    if (_equals) {
      _or = true;
    } else {
      String _name = feature.getName();
      boolean _isNullOrEmpty = Strings.isNullOrEmpty(_name);
      _or = (_equals || _isNullOrEmpty);
    }
    if (_or) {
      return;
    }
    Background _background = feature.getBackground();
    final JvmGenericType background = this.toClass(_background, acceptor);
    EList<Scenario> _scenarios = feature.getScenarios();
    final ArrayList<JvmGenericType> scenarios = this.toClass(_scenarios, acceptor, background);
    this.toClass(feature, acceptor, scenarios);
  }
  
  public Feature resolveFeature(final EObject root) {
    final FeatureFile featureFile = ((FeatureFile) root);
    EList<XtendClass> _xtendClasses = featureFile.getXtendClasses();
    boolean _isEmpty = _xtendClasses.isEmpty();
    if (_isEmpty) {
      return null;
    }
    EList<XtendClass> _xtendClasses_1 = featureFile.getXtendClasses();
    final XtendClass xtendClass = _xtendClasses_1.get(0);
    return ((Feature) xtendClass);
  }
  
  public JvmGenericType toClass(final Background background, final IJvmDeclaredTypeAcceptor acceptor) {
    JvmGenericType _xblockexpression = null;
    {
      boolean _equals = Objects.equal(background, null);
      if (_equals) {
        return null;
      }
      this.addSuperClass(background);
      final JvmGenericType backgroundClass = this.toClass(background);
      backgroundClass.setAbstract(true);
      List<JvmGenericType> _emptyList = CollectionLiterals.<JvmGenericType>emptyList();
      this.register(acceptor, background, backgroundClass, _emptyList);
      _xblockexpression = (backgroundClass);
    }
    return _xblockexpression;
  }
  
  public ArrayList<JvmGenericType> toClass(final List<Scenario> scenarios, final IJvmDeclaredTypeAcceptor acceptor, final JvmGenericType backgroundType) {
    final ArrayList<JvmGenericType> result = CollectionLiterals.<JvmGenericType>newArrayList();
    final Procedure1<Scenario> _function = new Procedure1<Scenario>() {
        public void apply(final Scenario it) {
          final JvmGenericType inferredJvmType = FeatureJvmModelInferrer.this.toClass(it, backgroundType);
          List<JvmGenericType> _emptyList = CollectionLiterals.<JvmGenericType>emptyList();
          FeatureJvmModelInferrer.this.register(acceptor, it, inferredJvmType, _emptyList);
          result.add(inferredJvmType);
        }
      };
    IterableExtensions.<Scenario>forEach(scenarios, _function);
    return result;
  }
  
  public void toClass(final Feature feature, final IJvmDeclaredTypeAcceptor acceptor, final List<JvmGenericType> scenarios) {
    final JvmGenericType inferredJvmType = this.toClass(feature);
    this.register(acceptor, feature, inferredJvmType, scenarios);
  }
  
  public void register(final IJvmDeclaredTypeAcceptor acceptor, final XtendClass source, final JvmGenericType inferredJvmType, final List<JvmGenericType> scenarios) {
    this._iJvmModelAssociator.associatePrimary(source, inferredJvmType);
    IPostIndexingInitializing<JvmGenericType> _accept = acceptor.<JvmGenericType>accept(inferredJvmType);
    final Procedure1<JvmGenericType> _function = new Procedure1<JvmGenericType>() {
        public void apply(final JvmGenericType it) {
          FeatureJvmModelInferrer.this.initialize(source, inferredJvmType, scenarios);
        }
      };
    _accept.initializeLater(_function);
  }
  
  public JvmGenericType toClass(final XtendClass xtendClass) {
    JvmGenericType _class = this.toClass(xtendClass, null);
    return _class;
  }
  
  public JvmGenericType toClass(final XtendClass xtendClass, final JvmGenericType superClass) {
    JvmGenericType _xblockexpression = null;
    {
      boolean _notEquals = (!Objects.equal(superClass, null));
      if (_notEquals) {
        JvmParameterizedTypeReference _createTypeRef = this._typeReferences.createTypeRef(superClass);
        xtendClass.setExtends(_createTypeRef);
      } else {
        this.addSuperClass(xtendClass);
      }
      String _javaClassName = this._featureClassNameProvider.toJavaClassName(xtendClass);
      final Procedure1<JvmGenericType> _function = new Procedure1<JvmGenericType>() {
          public void apply(final JvmGenericType it) {
            String _packageName = xtendClass.getPackageName();
            it.setPackageName(_packageName);
          }
        };
      JvmGenericType _class = this._extendedJvmTypesBuilder.toClass(xtendClass, _javaClassName, _function);
      _xblockexpression = (_class);
    }
    return _xblockexpression;
  }
  
  public void initialize(final XtendClass source, final JvmGenericType inferredJvmType, final List<JvmGenericType> scenarios) {
    this.init(source, inferredJvmType, scenarios);
  }
  
  protected void _init(final Feature feature, final JvmGenericType inferredJvmType, final List<JvmGenericType> scenarios) {
    final EList<JvmAnnotationReference> annotations = inferredJvmType.getAnnotations();
    SpecJvmModelProcessor _testRuntime = this.getTestRuntime();
    _testRuntime.updateFeature(feature, inferredJvmType);
    boolean _isEmpty = scenarios.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      SpecJvmModelProcessor _testRuntime_1 = this.getTestRuntime();
      _testRuntime_1.addChildren(feature, inferredJvmType, scenarios);
    }
    String _describe = this._stepNameProvider.describe(feature);
    JvmAnnotationReference _annotation = this._extendedJvmTypesBuilder.toAnnotation(feature, Named.class, _describe);
    this._extendedJvmTypesBuilder.<JvmAnnotationReference>operator_add(annotations, _annotation);
    super.initialize(feature, inferredJvmType);
  }
  
  protected void _init(final Scenario scenario, final JvmGenericType inferredJvmType, final List<JvmGenericType> scenarios) {
    this._stepReferenceFieldCreator.copyXtendMemberForReferences(scenario);
    EList<XtendMember> _members = scenario.getMembers();
    Iterable<XtendField> _filter = Iterables.<XtendField>filter(_members, XtendField.class);
    final Procedure1<XtendField> _function = new Procedure1<XtendField>() {
        public void apply(final XtendField it) {
          FeatureJvmModelInferrer.this.initializeName(it);
        }
      };
    IterableExtensions.<XtendField>forEach(_filter, _function);
    final EList<JvmAnnotationReference> annotations = inferredJvmType.getAnnotations();
    SpecJvmModelProcessor _testRuntime = this.getTestRuntime();
    _testRuntime.updateFeature(scenario, inferredJvmType);
    String _describe = this._stepNameProvider.describe(scenario);
    JvmAnnotationReference _annotation = this._extendedJvmTypesBuilder.toAnnotation(scenario, Named.class, _describe);
    this._extendedJvmTypesBuilder.<JvmAnnotationReference>operator_add(annotations, _annotation);
    final Feature feature = this.feature(scenario);
    int start = 0;
    EList<XAnnotation> _annotations = feature.getAnnotations();
    this._extendedJvmTypesBuilder.translateAnnotationsTo(_annotations, inferredJvmType);
    final Background background = feature.getBackground();
    boolean _and = false;
    boolean _not = (!(scenario instanceof Background));
    if (!_not) {
      _and = false;
    } else {
      boolean _notEquals = (!Objects.equal(background, null));
      _and = (_not && _notEquals);
    }
    if (_and) {
      ArrayList<Step> _allSteps = Scenarios.allSteps(background);
      int _generateBackgroundStepCalls = this.generateBackgroundStepCalls(_allSteps, inferredJvmType);
      start = _generateBackgroundStepCalls;
    }
    ArrayList<Step> _allSteps_1 = Scenarios.allSteps(scenario);
    this.generateSteps(_allSteps_1, inferredJvmType, start, scenario);
    super.initialize(scenario, inferredJvmType);
    ArrayList<Step> _allSteps_2 = Scenarios.allSteps(scenario);
    Iterable<StepReference> _filter_1 = Iterables.<StepReference>filter(_allSteps_2, StepReference.class);
    final Procedure1<StepReference> _function_1 = new Procedure1<StepReference>() {
        public void apply(final StepReference it) {
          StepImplementation _reference = it.getReference();
          boolean _equals = Objects.equal(_reference, null);
          if (_equals) {
            return;
          }
          StepImplementation _reference_1 = it.getReference();
          final Scenario original = EcoreUtil2.<Scenario>getContainerOfType(_reference_1, Scenario.class);
          boolean _equals_1 = Objects.equal(original, null);
          if (_equals_1) {
            return;
          }
          Set<EObject> _jvmElements = FeatureJvmModelInferrer.this._iJvmModelAssociations.getJvmElements(original);
          Iterable<JvmGenericType> _filter = Iterables.<JvmGenericType>filter(_jvmElements, JvmGenericType.class);
          final Function1<JvmGenericType,Boolean> _function = new Function1<JvmGenericType,Boolean>() {
              public Boolean apply(final JvmGenericType it) {
                EObject _primarySourceElement = FeatureJvmModelInferrer.this._iJvmModelAssociations.getPrimarySourceElement(it);
                boolean _equals = Objects.equal(_primarySourceElement, original);
                return Boolean.valueOf(_equals);
              }
            };
          final JvmGenericType originalType = IterableExtensions.<JvmGenericType>findFirst(_filter, _function);
          StepExpression _expressionOf = FeatureJvmModelInferrer.this._stepExpressionProvider.expressionOf(it);
          FeatureJvmModelInferrer.this._jvmFieldReferenceUpdater.updateReferences(_expressionOf, originalType, inferredJvmType);
        }
      };
    IterableExtensions.<StepReference>forEach(_filter_1, _function_1);
  }
  
  public void initializeName(final XtendField field) {
    String _name = field.getName();
    boolean _notEquals = (!Objects.equal(_name, null));
    if (_notEquals) {
      return;
    }
    String _computeFieldName = this.computeFieldName(field, null);
    field.setName(_computeFieldName);
  }
  
  public void generateStepValues(final Step step) {
    final List<String> arguments = this.stepArgumentsProvider.findStepArguments(step);
    final StepExpression stepExpression = step.getStepExpression();
    boolean _or = false;
    boolean _isEmpty = arguments.isEmpty();
    if (_isEmpty) {
      _or = true;
    } else {
      StepExpression _stepExpression = step.getStepExpression();
      boolean _equals = Objects.equal(_stepExpression, null);
      _or = (_isEmpty || _equals);
    }
    if (_or) {
      return;
    }
    TreeIterator<EObject> _eAllContents = stepExpression.eAllContents();
    Iterator<XVariableDeclaration> _filter = Iterators.<XVariableDeclaration>filter(_eAllContents, XVariableDeclaration.class);
    final Function1<XVariableDeclaration,Boolean> _function = new Function1<XVariableDeclaration,Boolean>() {
        public Boolean apply(final XVariableDeclaration it) {
          String _name = it.getName();
          boolean _equals = Objects.equal(_name, FeatureJvmModelInferrer.STEP_VALUES);
          return Boolean.valueOf(_equals);
        }
      };
    Iterator<XVariableDeclaration> decs = IteratorExtensions.<XVariableDeclaration>filter(_filter, _function);
    boolean _isEmpty_1 = IteratorExtensions.isEmpty(decs);
    if (_isEmpty_1) {
      return;
    }
    final XVariableDeclaration dec = IteratorExtensions.<XVariableDeclaration>head(decs);
    this.setStepValueType(dec, ((Step) step));
    if ((step instanceof StepImplementation)) {
      return;
    }
    TreeIterator<EObject> _eAllContents_1 = stepExpression.eAllContents();
    Iterator<XConstructorCall> calls = Iterators.<XConstructorCall>filter(_eAllContents_1, XConstructorCall.class);
    final XConstructorCall argsConstructor = IteratorExtensions.<XConstructorCall>head(calls);
    EList<XExpression> _arguments = argsConstructor.getArguments();
    _arguments.clear();
    final Procedure1<String> _function_1 = new Procedure1<String>() {
        public void apply(final String it) {
          final XStringLiteral arg = XbaseFactory.eINSTANCE.createXStringLiteral();
          arg.setValue(it);
          EList<XExpression> _arguments = argsConstructor.getArguments();
          FeatureJvmModelInferrer.this._extendedJvmTypesBuilder.<XStringLiteral>operator_add(_arguments, arg);
        }
      };
    IterableExtensions.<String>forEach(arguments, _function_1);
  }
  
  public void setStepValueType(final XVariableDeclaration variableDec, final Step step) {
    JvmTypeReference typeRef = this._typeReferences.getTypeForName(StepArguments.class, step);
    boolean _equals = Objects.equal(typeRef, null);
    if (_equals) {
      return;
    }
    variableDec.setType(typeRef);
    JvmType _type = typeRef.getType();
    final JvmGenericType type = ((JvmGenericType) _type);
    XExpression _right = variableDec.getRight();
    XConstructorCall constructor = ((XConstructorCall) _right);
    EList<JvmMember> _members = type.getMembers();
    Iterator<JvmMember> _iterator = _members.iterator();
    UnmodifiableIterator<JvmConstructor> _filter = Iterators.<JvmConstructor>filter(_iterator, JvmConstructor.class);
    JvmConstructor _next = _filter.next();
    constructor.setConstructor(_next);
  }
  
  public int generateBackgroundStepCalls(final Iterable<Step> steps, final JvmGenericType inferredJvmType) {
    int _xblockexpression = (int) 0;
    {
      int order = 0;
      for (final Step step : steps) {
        int _transformCalls = this.transformCalls(step, inferredJvmType, order);
        order = _transformCalls;
      }
      _xblockexpression = (order);
    }
    return _xblockexpression;
  }
  
  public int transformCalls(final Step step, final JvmGenericType inferredJvmType, final int order) {
    int _xblockexpression = (int) 0;
    {
      final String methodName = this._stepNameProvider.getMethodName(step);
      EList<JvmMember> _members = inferredJvmType.getMembers();
      JvmTypeReference _typeForName = this._typeReferences.getTypeForName(Void.TYPE, step);
      final Procedure1<JvmOperation> _function = new Procedure1<JvmOperation>() {
          public void apply(final JvmOperation it) {
            final Procedure1<ITreeAppendable> _function = new Procedure1<ITreeAppendable>() {
                public void apply(final ITreeAppendable a) {
                  String _plus = ("super." + methodName);
                  String _plus_1 = (_plus + "();");
                  a.append(_plus_1);
                }
              };
            FeatureJvmModelInferrer.this._extendedJvmTypesBuilder.setBody(it, _function);
            FeatureJvmModelInferrer.this.markAsPending(it, step);
            SpecJvmModelProcessor _testRuntime = FeatureJvmModelInferrer.this.getTestRuntime();
            _testRuntime.markAsTestMethod(step, it);
            EList<JvmAnnotationReference> _annotations = it.getAnnotations();
            int _intValue = Integer.valueOf(order).intValue();
            JvmAnnotationReference _annotation = FeatureJvmModelInferrer.this._extendedJvmTypesBuilder.toAnnotation(step, Order.class, Integer.valueOf(_intValue));
            FeatureJvmModelInferrer.this._extendedJvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations, _annotation);
            EList<JvmAnnotationReference> _annotations_1 = it.getAnnotations();
            String _describe = FeatureJvmModelInferrer.this._stepNameProvider.describe(step);
            JvmAnnotationReference _annotation_1 = FeatureJvmModelInferrer.this._extendedJvmTypesBuilder.toAnnotation(step, Named.class, _describe);
            FeatureJvmModelInferrer.this._extendedJvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations_1, _annotation_1);
          }
        };
      JvmOperation _method = this._extendedJvmTypesBuilder.toMethod(step, methodName, _typeForName, _function);
      this._extendedJvmTypesBuilder.<JvmOperation>operator_add(_members, _method);
      int _plus = (order + 1);
      _xblockexpression = (_plus);
    }
    return _xblockexpression;
  }
  
  public void generateSteps(final Iterable<Step> steps, final JvmGenericType inferredJvmType, final int start, final Scenario scenario) {
    int order = start;
    for (final Step step : steps) {
      int _transform = this.transform(step, inferredJvmType, order, scenario);
      order = _transform;
    }
  }
  
  public int transform(final Step step, final JvmGenericType inferredJvmType, final int order, final Scenario scenario) {
    int _xblockexpression = (int) 0;
    {
      EList<JvmMember> _members = inferredJvmType.getMembers();
      String _methodName = this._stepNameProvider.getMethodName(step);
      JvmTypeReference _typeForName = this._typeReferences.getTypeForName(Void.TYPE, step);
      final Procedure1<JvmOperation> _function = new Procedure1<JvmOperation>() {
          public void apply(final JvmOperation it) {
            final StepExpression stepExpression = FeatureJvmModelInferrer.this._stepExpressionProvider.expressionOf(step);
            XBlockExpression _blockExpression = stepExpression==null?(XBlockExpression)null:stepExpression.getBlockExpression();
            FeatureJvmModelInferrer.this._extendedJvmTypesBuilder.setBody(it, _blockExpression);
            FeatureJvmModelInferrer.this.generateStepValues(step);
            SpecJvmModelProcessor _testRuntime = FeatureJvmModelInferrer.this.getTestRuntime();
            _testRuntime.markAsTestMethod(step, it);
            EList<JvmAnnotationReference> _annotations = it.getAnnotations();
            int _intValue = Integer.valueOf(order).intValue();
            JvmAnnotationReference _annotation = FeatureJvmModelInferrer.this._extendedJvmTypesBuilder.toAnnotation(step, Order.class, Integer.valueOf(_intValue));
            FeatureJvmModelInferrer.this._extendedJvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations, _annotation);
            String name = FeatureJvmModelInferrer.this._stepNameProvider.describe(step);
            FeatureJvmModelInferrer.this.markAsPending(it, step);
            EList<JvmAnnotationReference> _annotations_1 = it.getAnnotations();
            JvmAnnotationReference _annotation_1 = FeatureJvmModelInferrer.this._extendedJvmTypesBuilder.toAnnotation(step, Named.class, name);
            FeatureJvmModelInferrer.this._extendedJvmTypesBuilder.<JvmAnnotationReference>operator_add(_annotations_1, _annotation_1);
          }
        };
      JvmOperation _method = this._extendedJvmTypesBuilder.toMethod(step, _methodName, _typeForName, _function);
      this._extendedJvmTypesBuilder.<JvmOperation>operator_add(_members, _method);
      int _plus = (order + 1);
      _xblockexpression = (_plus);
    }
    return _xblockexpression;
  }
  
  public Feature feature(final EObject context) {
    Feature _containerOfType = EcoreUtil2.<Feature>getContainerOfType(context, Feature.class);
    return _containerOfType;
  }
  
  public void markAsPending(final JvmOperation operation, final Step step) {
    boolean _isPending = step.isPending();
    if (_isPending) {
      SpecJvmModelProcessor _testRuntime = this.getTestRuntime();
      _testRuntime.markAsPending(step, operation);
    }
  }
  
  public void init(final EObject feature, final JvmGenericType inferredJvmType, final List<JvmGenericType> scenarios) {
    if (feature instanceof Feature) {
      _init((Feature)feature, inferredJvmType, scenarios);
      return;
    } else if (feature instanceof Scenario) {
      _init((Scenario)feature, inferredJvmType, scenarios);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature, inferredJvmType, scenarios).toString());
    }
  }
}
