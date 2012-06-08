/**
 * <copyright>
 * </copyright>
 *
 */
package org.jnario.suite.suite;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.xtend.core.xtend.XtendPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.jnario.suite.suite.SuiteFactory
 * @model kind="package"
 * @generated
 */
public interface SuitePackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "suite";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.jnario.org/suite/Suite";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "suite";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SuitePackage eINSTANCE = org.jnario.suite.suite.impl.SuitePackageImpl.init();

  /**
   * The meta object id for the '{@link org.jnario.suite.suite.impl.ReferenceImpl <em>Reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jnario.suite.suite.impl.ReferenceImpl
   * @see org.jnario.suite.suite.impl.SuitePackageImpl#getReference()
   * @generated
   */
  int REFERENCE = 0;

  /**
   * The number of structural features of the '<em>Reference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFERENCE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.jnario.suite.suite.impl.PatternReferenceImpl <em>Pattern Reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jnario.suite.suite.impl.PatternReferenceImpl
   * @see org.jnario.suite.suite.impl.SuitePackageImpl#getPatternReference()
   * @generated
   */
  int PATTERN_REFERENCE = 1;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATTERN_REFERENCE__PATTERN = REFERENCE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Pattern Reference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATTERN_REFERENCE_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.jnario.suite.suite.impl.SpecReferenceImpl <em>Spec Reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jnario.suite.suite.impl.SpecReferenceImpl
   * @see org.jnario.suite.suite.impl.SuitePackageImpl#getSpecReference()
   * @generated
   */
  int SPEC_REFERENCE = 2;

  /**
   * The feature id for the '<em><b>Spec</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SPEC_REFERENCE__SPEC = REFERENCE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Text</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SPEC_REFERENCE__TEXT = REFERENCE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Spec Reference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SPEC_REFERENCE_FEATURE_COUNT = REFERENCE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.jnario.suite.suite.impl.SuiteImpl <em>Suite</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.jnario.suite.suite.impl.SuiteImpl
   * @see org.jnario.suite.suite.impl.SuitePackageImpl#getSuite()
   * @generated
   */
  int SUITE = 3;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUITE__ANNOTATIONS = XtendPackage.XTEND_CLASS__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUITE__NAME = XtendPackage.XTEND_CLASS__NAME;

  /**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUITE__ABSTRACT = XtendPackage.XTEND_CLASS__ABSTRACT;

  /**
   * The feature id for the '<em><b>Extends</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUITE__EXTENDS = XtendPackage.XTEND_CLASS__EXTENDS;

  /**
   * The feature id for the '<em><b>Implements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUITE__IMPLEMENTS = XtendPackage.XTEND_CLASS__IMPLEMENTS;

  /**
   * The feature id for the '<em><b>Super Types</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUITE__SUPER_TYPES = XtendPackage.XTEND_CLASS__SUPER_TYPES;

  /**
   * The feature id for the '<em><b>Members</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUITE__MEMBERS = XtendPackage.XTEND_CLASS__MEMBERS;

  /**
   * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUITE__TYPE_PARAMETERS = XtendPackage.XTEND_CLASS__TYPE_PARAMETERS;

  /**
   * The feature id for the '<em><b>Specs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUITE__SPECS = XtendPackage.XTEND_CLASS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Suite</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SUITE_FEATURE_COUNT = XtendPackage.XTEND_CLASS_FEATURE_COUNT + 1;


  /**
   * Returns the meta object for class '{@link org.jnario.suite.suite.Reference <em>Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Reference</em>'.
   * @see org.jnario.suite.suite.Reference
   * @generated
   */
  EClass getReference();

  /**
   * Returns the meta object for class '{@link org.jnario.suite.suite.PatternReference <em>Pattern Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pattern Reference</em>'.
   * @see org.jnario.suite.suite.PatternReference
   * @generated
   */
  EClass getPatternReference();

  /**
   * Returns the meta object for the attribute '{@link org.jnario.suite.suite.PatternReference#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pattern</em>'.
   * @see org.jnario.suite.suite.PatternReference#getPattern()
   * @see #getPatternReference()
   * @generated
   */
  EAttribute getPatternReference_Pattern();

  /**
   * Returns the meta object for class '{@link org.jnario.suite.suite.SpecReference <em>Spec Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Spec Reference</em>'.
   * @see org.jnario.suite.suite.SpecReference
   * @generated
   */
  EClass getSpecReference();

  /**
   * Returns the meta object for the reference '{@link org.jnario.suite.suite.SpecReference#getSpec <em>Spec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Spec</em>'.
   * @see org.jnario.suite.suite.SpecReference#getSpec()
   * @see #getSpecReference()
   * @generated
   */
  EReference getSpecReference_Spec();

  /**
   * Returns the meta object for the attribute '{@link org.jnario.suite.suite.SpecReference#getText <em>Text</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Text</em>'.
   * @see org.jnario.suite.suite.SpecReference#getText()
   * @see #getSpecReference()
   * @generated
   */
  EAttribute getSpecReference_Text();

  /**
   * Returns the meta object for class '{@link org.jnario.suite.suite.Suite <em>Suite</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Suite</em>'.
   * @see org.jnario.suite.suite.Suite
   * @generated
   */
  EClass getSuite();

  /**
   * Returns the meta object for the containment reference list '{@link org.jnario.suite.suite.Suite#getSpecs <em>Specs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Specs</em>'.
   * @see org.jnario.suite.suite.Suite#getSpecs()
   * @see #getSuite()
   * @generated
   */
  EReference getSuite_Specs();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  SuiteFactory getSuiteFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.jnario.suite.suite.impl.ReferenceImpl <em>Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jnario.suite.suite.impl.ReferenceImpl
     * @see org.jnario.suite.suite.impl.SuitePackageImpl#getReference()
     * @generated
     */
    EClass REFERENCE = eINSTANCE.getReference();

    /**
     * The meta object literal for the '{@link org.jnario.suite.suite.impl.PatternReferenceImpl <em>Pattern Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jnario.suite.suite.impl.PatternReferenceImpl
     * @see org.jnario.suite.suite.impl.SuitePackageImpl#getPatternReference()
     * @generated
     */
    EClass PATTERN_REFERENCE = eINSTANCE.getPatternReference();

    /**
     * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PATTERN_REFERENCE__PATTERN = eINSTANCE.getPatternReference_Pattern();

    /**
     * The meta object literal for the '{@link org.jnario.suite.suite.impl.SpecReferenceImpl <em>Spec Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jnario.suite.suite.impl.SpecReferenceImpl
     * @see org.jnario.suite.suite.impl.SuitePackageImpl#getSpecReference()
     * @generated
     */
    EClass SPEC_REFERENCE = eINSTANCE.getSpecReference();

    /**
     * The meta object literal for the '<em><b>Spec</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SPEC_REFERENCE__SPEC = eINSTANCE.getSpecReference_Spec();

    /**
     * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SPEC_REFERENCE__TEXT = eINSTANCE.getSpecReference_Text();

    /**
     * The meta object literal for the '{@link org.jnario.suite.suite.impl.SuiteImpl <em>Suite</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.jnario.suite.suite.impl.SuiteImpl
     * @see org.jnario.suite.suite.impl.SuitePackageImpl#getSuite()
     * @generated
     */
    EClass SUITE = eINSTANCE.getSuite();

    /**
     * The meta object literal for the '<em><b>Specs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SUITE__SPECS = eINSTANCE.getSuite_Specs();

  }

} //SuitePackage