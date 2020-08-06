/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 3 Aug 2020, 09:33:05                        ---
 * ----------------------------------------------------------------
 */
package com.dynamic.yield.jalo.cronjobs;

import com.dynamic.yield.constants.DynamicyieldaddonConstants;
import com.dynamic.yield.jalo.cronjobs.DynamicYieldExportProductFeedCronJob;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.dynamic.yield.jalo.cronjobs.DynamicYieldExportProperty DynamicYieldExportProperty}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedDynamicYieldExportProperty extends GenericItem
{
	/** Qualifier of the <code>DynamicYieldExportProperty.productProperty</code> attribute **/
	public static final String PRODUCTPROPERTY = "productProperty";
	/** Qualifier of the <code>DynamicYieldExportProperty.dynamicYieldProperty</code> attribute **/
	public static final String DYNAMICYIELDPROPERTY = "dynamicYieldProperty";
	/** Qualifier of the <code>DynamicYieldExportProperty.active</code> attribute **/
	public static final String ACTIVE = "active";
	/** Qualifier of the <code>DynamicYieldExportProperty.beanValueProvider</code> attribute **/
	public static final String BEANVALUEPROVIDER = "beanValueProvider";
	/** Qualifier of the <code>DynamicYieldExportProperty.multilanguage</code> attribute **/
	public static final String MULTILANGUAGE = "multilanguage";
	/** Qualifier of the <code>DynamicYieldExportProperty.mandatory</code> attribute **/
	public static final String MANDATORY = "mandatory";
	/** Qualifier of the <code>DynamicYieldExportProperty.exportCronJob</code> attribute **/
	public static final String EXPORTCRONJOB = "exportCronJob";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n EXPORTCRONJOB's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedDynamicYieldExportProperty> EXPORTCRONJOBHANDLER = new BidirectionalOneToManyHandler<GeneratedDynamicYieldExportProperty>(
	DynamicyieldaddonConstants.TC.DYNAMICYIELDEXPORTPROPERTY,
	false,
	"exportCronJob",
	null,
	false,
	true,
	CollectionType.SET
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(PRODUCTPROPERTY, AttributeMode.INITIAL);
		tmp.put(DYNAMICYIELDPROPERTY, AttributeMode.INITIAL);
		tmp.put(ACTIVE, AttributeMode.INITIAL);
		tmp.put(BEANVALUEPROVIDER, AttributeMode.INITIAL);
		tmp.put(MULTILANGUAGE, AttributeMode.INITIAL);
		tmp.put(MANDATORY, AttributeMode.INITIAL);
		tmp.put(EXPORTCRONJOB, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.active</code> attribute.
	 * @return the active
	 */
	public Boolean isActive(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ACTIVE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.active</code> attribute.
	 * @return the active
	 */
	public Boolean isActive()
	{
		return isActive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.active</code> attribute. 
	 * @return the active
	 */
	public boolean isActiveAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isActive( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.active</code> attribute. 
	 * @return the active
	 */
	public boolean isActiveAsPrimitive()
	{
		return isActiveAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.active</code> attribute. 
	 * @param value the active
	 */
	public void setActive(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ACTIVE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.active</code> attribute. 
	 * @param value the active
	 */
	public void setActive(final Boolean value)
	{
		setActive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.active</code> attribute. 
	 * @param value the active
	 */
	public void setActive(final SessionContext ctx, final boolean value)
	{
		setActive( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.active</code> attribute. 
	 * @param value the active
	 */
	public void setActive(final boolean value)
	{
		setActive( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.beanValueProvider</code> attribute.
	 * @return the beanValueProvider
	 */
	public String getBeanValueProvider(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BEANVALUEPROVIDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.beanValueProvider</code> attribute.
	 * @return the beanValueProvider
	 */
	public String getBeanValueProvider()
	{
		return getBeanValueProvider( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.beanValueProvider</code> attribute. 
	 * @param value the beanValueProvider
	 */
	public void setBeanValueProvider(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BEANVALUEPROVIDER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.beanValueProvider</code> attribute. 
	 * @param value the beanValueProvider
	 */
	public void setBeanValueProvider(final String value)
	{
		setBeanValueProvider( getSession().getSessionContext(), value );
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		EXPORTCRONJOBHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.dynamicYieldProperty</code> attribute.
	 * @return the dynamicYieldProperty
	 */
	public String getDynamicYieldProperty(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DYNAMICYIELDPROPERTY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.dynamicYieldProperty</code> attribute.
	 * @return the dynamicYieldProperty
	 */
	public String getDynamicYieldProperty()
	{
		return getDynamicYieldProperty( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.dynamicYieldProperty</code> attribute. 
	 * @param value the dynamicYieldProperty
	 */
	public void setDynamicYieldProperty(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DYNAMICYIELDPROPERTY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.dynamicYieldProperty</code> attribute. 
	 * @param value the dynamicYieldProperty
	 */
	public void setDynamicYieldProperty(final String value)
	{
		setDynamicYieldProperty( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.exportCronJob</code> attribute.
	 * @return the exportCronJob
	 */
	public DynamicYieldExportProductFeedCronJob getExportCronJob(final SessionContext ctx)
	{
		return (DynamicYieldExportProductFeedCronJob)getProperty( ctx, EXPORTCRONJOB);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.exportCronJob</code> attribute.
	 * @return the exportCronJob
	 */
	public DynamicYieldExportProductFeedCronJob getExportCronJob()
	{
		return getExportCronJob( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.exportCronJob</code> attribute. 
	 * @param value the exportCronJob
	 */
	public void setExportCronJob(final SessionContext ctx, final DynamicYieldExportProductFeedCronJob value)
	{
		EXPORTCRONJOBHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.exportCronJob</code> attribute. 
	 * @param value the exportCronJob
	 */
	public void setExportCronJob(final DynamicYieldExportProductFeedCronJob value)
	{
		setExportCronJob( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.mandatory</code> attribute.
	 * @return the mandatory
	 */
	public Boolean isMandatory(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, MANDATORY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.mandatory</code> attribute.
	 * @return the mandatory
	 */
	public Boolean isMandatory()
	{
		return isMandatory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.mandatory</code> attribute. 
	 * @return the mandatory
	 */
	public boolean isMandatoryAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isMandatory( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.mandatory</code> attribute. 
	 * @return the mandatory
	 */
	public boolean isMandatoryAsPrimitive()
	{
		return isMandatoryAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.mandatory</code> attribute. 
	 * @param value the mandatory
	 */
	public void setMandatory(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, MANDATORY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.mandatory</code> attribute. 
	 * @param value the mandatory
	 */
	public void setMandatory(final Boolean value)
	{
		setMandatory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.mandatory</code> attribute. 
	 * @param value the mandatory
	 */
	public void setMandatory(final SessionContext ctx, final boolean value)
	{
		setMandatory( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.mandatory</code> attribute. 
	 * @param value the mandatory
	 */
	public void setMandatory(final boolean value)
	{
		setMandatory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.multilanguage</code> attribute.
	 * @return the multilanguage
	 */
	public Boolean isMultilanguage(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, MULTILANGUAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.multilanguage</code> attribute.
	 * @return the multilanguage
	 */
	public Boolean isMultilanguage()
	{
		return isMultilanguage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.multilanguage</code> attribute. 
	 * @return the multilanguage
	 */
	public boolean isMultilanguageAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isMultilanguage( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.multilanguage</code> attribute. 
	 * @return the multilanguage
	 */
	public boolean isMultilanguageAsPrimitive()
	{
		return isMultilanguageAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.multilanguage</code> attribute. 
	 * @param value the multilanguage
	 */
	public void setMultilanguage(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, MULTILANGUAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.multilanguage</code> attribute. 
	 * @param value the multilanguage
	 */
	public void setMultilanguage(final Boolean value)
	{
		setMultilanguage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.multilanguage</code> attribute. 
	 * @param value the multilanguage
	 */
	public void setMultilanguage(final SessionContext ctx, final boolean value)
	{
		setMultilanguage( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.multilanguage</code> attribute. 
	 * @param value the multilanguage
	 */
	public void setMultilanguage(final boolean value)
	{
		setMultilanguage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.productProperty</code> attribute.
	 * @return the productProperty
	 */
	public String getProductProperty(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PRODUCTPROPERTY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProperty.productProperty</code> attribute.
	 * @return the productProperty
	 */
	public String getProductProperty()
	{
		return getProductProperty( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.productProperty</code> attribute. 
	 * @param value the productProperty
	 */
	public void setProductProperty(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PRODUCTPROPERTY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProperty.productProperty</code> attribute. 
	 * @param value the productProperty
	 */
	public void setProductProperty(final String value)
	{
		setProductProperty( getSession().getSessionContext(), value );
	}
	
}
