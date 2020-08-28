/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 28 Aug 2020, 11:43:38                       ---
 * ----------------------------------------------------------------
 */
package com.dynamic.yield.jalo.cronjobs;

import com.dynamic.yield.constants.DynamicyieldaddonConstants;
import com.dynamic.yield.jalo.cronjobs.DynamicYieldExportProperty;
import de.hybris.platform.basecommerce.jalo.site.BaseSite;
import de.hybris.platform.catalog.jalo.CatalogVersion;
import de.hybris.platform.cronjob.jalo.CronJob;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.store.BaseStore;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Generated class for type {@link com.dynamic.yield.jalo.cronjobs.DynamicYieldExportProductFeedCronJob DynamicYieldExportProductFeedCronJob}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedDynamicYieldExportProductFeedCronJob extends CronJob
{
	/** Qualifier of the <code>DynamicYieldExportProductFeedCronJob.productCatalog</code> attribute **/
	public static final String PRODUCTCATALOG = "productCatalog";
	/** Qualifier of the <code>DynamicYieldExportProductFeedCronJob.baseStore</code> attribute **/
	public static final String BASESTORE = "baseStore";
	/** Qualifier of the <code>DynamicYieldExportProductFeedCronJob.baseSite</code> attribute **/
	public static final String BASESITE = "baseSite";
	/** Qualifier of the <code>DynamicYieldExportProductFeedCronJob.exportProperties</code> attribute **/
	public static final String EXPORTPROPERTIES = "exportProperties";
	/**
	* {@link OneToManyHandler} for handling 1:n EXPORTPROPERTIES's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<DynamicYieldExportProperty> EXPORTPROPERTIESHANDLER = new OneToManyHandler<DynamicYieldExportProperty>(
	DynamicyieldaddonConstants.TC.DYNAMICYIELDEXPORTPROPERTY,
	false,
	"exportCronJob",
	null,
	false,
	true,
	CollectionType.SET
	).withRelationQualifier("exportProperties");
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CronJob.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(PRODUCTCATALOG, AttributeMode.INITIAL);
		tmp.put(BASESTORE, AttributeMode.INITIAL);
		tmp.put(BASESITE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProductFeedCronJob.baseSite</code> attribute.
	 * @return the baseSite
	 */
	public BaseSite getBaseSite(final SessionContext ctx)
	{
		return (BaseSite)getProperty( ctx, BASESITE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProductFeedCronJob.baseSite</code> attribute.
	 * @return the baseSite
	 */
	public BaseSite getBaseSite()
	{
		return getBaseSite( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProductFeedCronJob.baseSite</code> attribute. 
	 * @param value the baseSite
	 */
	public void setBaseSite(final SessionContext ctx, final BaseSite value)
	{
		setProperty(ctx, BASESITE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProductFeedCronJob.baseSite</code> attribute. 
	 * @param value the baseSite
	 */
	public void setBaseSite(final BaseSite value)
	{
		setBaseSite( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProductFeedCronJob.baseStore</code> attribute.
	 * @return the baseStore
	 */
	public BaseStore getBaseStore(final SessionContext ctx)
	{
		return (BaseStore)getProperty( ctx, BASESTORE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProductFeedCronJob.baseStore</code> attribute.
	 * @return the baseStore
	 */
	public BaseStore getBaseStore()
	{
		return getBaseStore( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProductFeedCronJob.baseStore</code> attribute. 
	 * @param value the baseStore
	 */
	public void setBaseStore(final SessionContext ctx, final BaseStore value)
	{
		setProperty(ctx, BASESTORE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProductFeedCronJob.baseStore</code> attribute. 
	 * @param value the baseStore
	 */
	public void setBaseStore(final BaseStore value)
	{
		setBaseStore( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProductFeedCronJob.exportProperties</code> attribute.
	 * @return the exportProperties
	 */
	public Set<DynamicYieldExportProperty> getExportProperties(final SessionContext ctx)
	{
		return (Set<DynamicYieldExportProperty>)EXPORTPROPERTIESHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProductFeedCronJob.exportProperties</code> attribute.
	 * @return the exportProperties
	 */
	public Set<DynamicYieldExportProperty> getExportProperties()
	{
		return getExportProperties( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProductFeedCronJob.exportProperties</code> attribute. 
	 * @param value the exportProperties
	 */
	public void setExportProperties(final SessionContext ctx, final Set<DynamicYieldExportProperty> value)
	{
		EXPORTPROPERTIESHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProductFeedCronJob.exportProperties</code> attribute. 
	 * @param value the exportProperties
	 */
	public void setExportProperties(final Set<DynamicYieldExportProperty> value)
	{
		setExportProperties( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to exportProperties. 
	 * @param value the item to add to exportProperties
	 */
	public void addToExportProperties(final SessionContext ctx, final DynamicYieldExportProperty value)
	{
		EXPORTPROPERTIESHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to exportProperties. 
	 * @param value the item to add to exportProperties
	 */
	public void addToExportProperties(final DynamicYieldExportProperty value)
	{
		addToExportProperties( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from exportProperties. 
	 * @param value the item to remove from exportProperties
	 */
	public void removeFromExportProperties(final SessionContext ctx, final DynamicYieldExportProperty value)
	{
		EXPORTPROPERTIESHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from exportProperties. 
	 * @param value the item to remove from exportProperties
	 */
	public void removeFromExportProperties(final DynamicYieldExportProperty value)
	{
		removeFromExportProperties( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProductFeedCronJob.productCatalog</code> attribute.
	 * @return the productCatalog
	 */
	public CatalogVersion getProductCatalog(final SessionContext ctx)
	{
		return (CatalogVersion)getProperty( ctx, PRODUCTCATALOG);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldExportProductFeedCronJob.productCatalog</code> attribute.
	 * @return the productCatalog
	 */
	public CatalogVersion getProductCatalog()
	{
		return getProductCatalog( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProductFeedCronJob.productCatalog</code> attribute. 
	 * @param value the productCatalog
	 */
	public void setProductCatalog(final SessionContext ctx, final CatalogVersion value)
	{
		setProperty(ctx, PRODUCTCATALOG,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldExportProductFeedCronJob.productCatalog</code> attribute. 
	 * @param value the productCatalog
	 */
	public void setProductCatalog(final CatalogVersion value)
	{
		setProductCatalog( getSession().getSessionContext(), value );
	}
	
}
