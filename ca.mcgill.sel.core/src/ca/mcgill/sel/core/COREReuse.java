/**
 */
package ca.mcgill.sel.core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Reuse</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link ca.mcgill.sel.core.COREReuse#getReusedConcern <em>Reused Concern</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREReuse#getConfigurations <em>Configurations</em>}</li>
 *   <li>{@link ca.mcgill.sel.core.COREReuse#getSelectedConfiguration <em>Selected Configuration</em>}</li>
 * </ul>
 *
 * @see ca.mcgill.sel.core.CorePackage#getCOREReuse()
 * @model
 * @generated
 */
public interface COREReuse extends CORENamedElement {
    /**
     * Returns the value of the '<em><b>Reused Concern</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reused Concern</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Reused Concern</em>' reference.
     * @see #setReusedConcern(COREConcern)
     * @see ca.mcgill.sel.core.CorePackage#getCOREReuse_ReusedConcern()
     * @model required="true"
     * @generated
     */
    COREConcern getReusedConcern();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREReuse#getReusedConcern <em>Reused Concern</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Reused Concern</em>' reference.
     * @see #getReusedConcern()
     * @generated
     */
    void setReusedConcern(COREConcern value);

    /**
     * Returns the value of the '<em><b>Configurations</b></em>' containment reference list.
     * The list contents are of type {@link ca.mcgill.sel.core.COREReuseConfiguration}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Configurations</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Configurations</em>' containment reference list.
     * @see ca.mcgill.sel.core.CorePackage#getCOREReuse_Configurations()
     * @model containment="true"
     * @generated
     */
    EList<COREReuseConfiguration> getConfigurations();

    /**
     * Returns the value of the '<em><b>Selected Configuration</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Selected Configuration</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Selected Configuration</em>' reference.
     * @see #setSelectedConfiguration(COREReuseConfiguration)
     * @see ca.mcgill.sel.core.CorePackage#getCOREReuse_SelectedConfiguration()
     * @model
     * @generated
     */
    COREReuseConfiguration getSelectedConfiguration();

    /**
     * Sets the value of the '{@link ca.mcgill.sel.core.COREReuse#getSelectedConfiguration <em>Selected Configuration</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Selected Configuration</em>' reference.
     * @see #getSelectedConfiguration()
     * @generated
     */
    void setSelectedConfiguration(COREReuseConfiguration value);

} // COREReuse
