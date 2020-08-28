/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 28 Aug 2020, 11:43:38                       ---
 * ----------------------------------------------------------------
 */
package com.dynamic.yield.jalo;

import com.dynamic.yield.constants.DynamicyieldaddonConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem DynamicYieldIntegration}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedDynamicYieldIntegration extends GenericItem
{
	/** Qualifier of the <code>DynamicYieldIntegration.siteID</code> attribute **/
	public static final String SITEID = "siteID";
	/** Qualifier of the <code>DynamicYieldIntegration.accessKeyID</code> attribute **/
	public static final String ACCESSKEYID = "accessKeyID";
	/** Qualifier of the <code>DynamicYieldIntegration.secretAccessKey</code> attribute **/
	public static final String SECRETACCESSKEY = "secretAccessKey";
	/** Qualifier of the <code>DynamicYieldIntegration.syncRate</code> attribute **/
	public static final String SYNCRATE = "syncRate";
	/** Qualifier of the <code>DynamicYieldIntegration.cdnLocation</code> attribute **/
	public static final String CDNLOCATION = "cdnLocation";
	/** Qualifier of the <code>DynamicYieldIntegration.cdnLocationCustomURL</code> attribute **/
	public static final String CDNLOCATIONCUSTOMURL = "cdnLocationCustomURL";
	/** Qualifier of the <code>DynamicYieldIntegration.productFeedUploadLocation</code> attribute **/
	public static final String PRODUCTFEEDUPLOADLOCATION = "productFeedUploadLocation";
	/** Qualifier of the <code>DynamicYieldIntegration.productFeedUploadLocationCustomURL</code> attribute **/
	public static final String PRODUCTFEEDUPLOADLOCATIONCUSTOMURL = "productFeedUploadLocationCustomURL";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(SITEID, AttributeMode.INITIAL);
		tmp.put(ACCESSKEYID, AttributeMode.INITIAL);
		tmp.put(SECRETACCESSKEY, AttributeMode.INITIAL);
		tmp.put(SYNCRATE, AttributeMode.INITIAL);
		tmp.put(CDNLOCATION, AttributeMode.INITIAL);
		tmp.put(CDNLOCATIONCUSTOMURL, AttributeMode.INITIAL);
		tmp.put(PRODUCTFEEDUPLOADLOCATION, AttributeMode.INITIAL);
		tmp.put(PRODUCTFEEDUPLOADLOCATIONCUSTOMURL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.accessKeyID</code> attribute.
	 * @return the accessKeyID - AWS S3 Access Key.
	 */
	public String getAccessKeyID(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ACCESSKEYID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.accessKeyID</code> attribute.
	 * @return the accessKeyID - AWS S3 Access Key.
	 */
	public String getAccessKeyID()
	{
		return getAccessKeyID( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.accessKeyID</code> attribute. 
	 * @param value the accessKeyID - AWS S3 Access Key.
	 */
	public void setAccessKeyID(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ACCESSKEYID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.accessKeyID</code> attribute. 
	 * @param value the accessKeyID - AWS S3 Access Key.
	 */
	public void setAccessKeyID(final String value)
	{
		setAccessKeyID( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.cdnLocation</code> attribute.
	 * @return the cdnLocation - CDN location option.
	 */
	public EnumerationValue getCdnLocation(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, CDNLOCATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.cdnLocation</code> attribute.
	 * @return the cdnLocation - CDN location option.
	 */
	public EnumerationValue getCdnLocation()
	{
		return getCdnLocation( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.cdnLocation</code> attribute. 
	 * @param value the cdnLocation - CDN location option.
	 */
	public void setCdnLocation(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, CDNLOCATION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.cdnLocation</code> attribute. 
	 * @param value the cdnLocation - CDN location option.
	 */
	public void setCdnLocation(final EnumerationValue value)
	{
		setCdnLocation( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.cdnLocationCustomURL</code> attribute.
	 * @return the cdnLocationCustomURL - CDN location custom URL.
	 */
	public String getCdnLocationCustomURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CDNLOCATIONCUSTOMURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.cdnLocationCustomURL</code> attribute.
	 * @return the cdnLocationCustomURL - CDN location custom URL.
	 */
	public String getCdnLocationCustomURL()
	{
		return getCdnLocationCustomURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.cdnLocationCustomURL</code> attribute. 
	 * @param value the cdnLocationCustomURL - CDN location custom URL.
	 */
	public void setCdnLocationCustomURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CDNLOCATIONCUSTOMURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.cdnLocationCustomURL</code> attribute. 
	 * @param value the cdnLocationCustomURL - CDN location custom URL.
	 */
	public void setCdnLocationCustomURL(final String value)
	{
		setCdnLocationCustomURL( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.productFeedUploadLocation</code> attribute.
	 * @return the productFeedUploadLocation - Product feed upload location option.
	 */
	public EnumerationValue getProductFeedUploadLocation(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, PRODUCTFEEDUPLOADLOCATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.productFeedUploadLocation</code> attribute.
	 * @return the productFeedUploadLocation - Product feed upload location option.
	 */
	public EnumerationValue getProductFeedUploadLocation()
	{
		return getProductFeedUploadLocation( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.productFeedUploadLocation</code> attribute. 
	 * @param value the productFeedUploadLocation - Product feed upload location option.
	 */
	public void setProductFeedUploadLocation(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, PRODUCTFEEDUPLOADLOCATION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.productFeedUploadLocation</code> attribute. 
	 * @param value the productFeedUploadLocation - Product feed upload location option.
	 */
	public void setProductFeedUploadLocation(final EnumerationValue value)
	{
		setProductFeedUploadLocation( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.productFeedUploadLocationCustomURL</code> attribute.
	 * @return the productFeedUploadLocationCustomURL - Product feed upload location custom URL.
	 */
	public String getProductFeedUploadLocationCustomURL(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PRODUCTFEEDUPLOADLOCATIONCUSTOMURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.productFeedUploadLocationCustomURL</code> attribute.
	 * @return the productFeedUploadLocationCustomURL - Product feed upload location custom URL.
	 */
	public String getProductFeedUploadLocationCustomURL()
	{
		return getProductFeedUploadLocationCustomURL( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.productFeedUploadLocationCustomURL</code> attribute. 
	 * @param value the productFeedUploadLocationCustomURL - Product feed upload location custom URL.
	 */
	public void setProductFeedUploadLocationCustomURL(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PRODUCTFEEDUPLOADLOCATIONCUSTOMURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.productFeedUploadLocationCustomURL</code> attribute. 
	 * @param value the productFeedUploadLocationCustomURL - Product feed upload location custom URL.
	 */
	public void setProductFeedUploadLocationCustomURL(final String value)
	{
		setProductFeedUploadLocationCustomURL( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.secretAccessKey</code> attribute.
	 * @return the secretAccessKey - AWS S3 Secret Key.
	 */
	public String getSecretAccessKey(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SECRETACCESSKEY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.secretAccessKey</code> attribute.
	 * @return the secretAccessKey - AWS S3 Secret Key.
	 */
	public String getSecretAccessKey()
	{
		return getSecretAccessKey( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.secretAccessKey</code> attribute. 
	 * @param value the secretAccessKey - AWS S3 Secret Key.
	 */
	public void setSecretAccessKey(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SECRETACCESSKEY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.secretAccessKey</code> attribute. 
	 * @param value the secretAccessKey - AWS S3 Secret Key.
	 */
	public void setSecretAccessKey(final String value)
	{
		setSecretAccessKey( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.siteID</code> attribute.
	 * @return the siteID - Dynamic Yield section ID.
	 */
	public Long getSiteID(final SessionContext ctx)
	{
		return (Long)getProperty( ctx, SITEID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.siteID</code> attribute.
	 * @return the siteID - Dynamic Yield section ID.
	 */
	public Long getSiteID()
	{
		return getSiteID( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.siteID</code> attribute. 
	 * @return the siteID - Dynamic Yield section ID.
	 */
	public long getSiteIDAsPrimitive(final SessionContext ctx)
	{
		Long value = getSiteID( ctx );
		return value != null ? value.longValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.siteID</code> attribute. 
	 * @return the siteID - Dynamic Yield section ID.
	 */
	public long getSiteIDAsPrimitive()
	{
		return getSiteIDAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.siteID</code> attribute. 
	 * @param value the siteID - Dynamic Yield section ID.
	 */
	public void setSiteID(final SessionContext ctx, final Long value)
	{
		setProperty(ctx, SITEID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.siteID</code> attribute. 
	 * @param value the siteID - Dynamic Yield section ID.
	 */
	public void setSiteID(final Long value)
	{
		setSiteID( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.siteID</code> attribute. 
	 * @param value the siteID - Dynamic Yield section ID.
	 */
	public void setSiteID(final SessionContext ctx, final long value)
	{
		setSiteID( ctx,Long.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.siteID</code> attribute. 
	 * @param value the siteID - Dynamic Yield section ID.
	 */
	public void setSiteID(final long value)
	{
		setSiteID( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.syncRate</code> attribute.
	 * @return the syncRate - Number of hours between syncs of product feed.
	 */
	public EnumerationValue getSyncRate(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, SYNCRATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>DynamicYieldIntegration.syncRate</code> attribute.
	 * @return the syncRate - Number of hours between syncs of product feed.
	 */
	public EnumerationValue getSyncRate()
	{
		return getSyncRate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.syncRate</code> attribute. 
	 * @param value the syncRate - Number of hours between syncs of product feed.
	 */
	public void setSyncRate(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, SYNCRATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>DynamicYieldIntegration.syncRate</code> attribute. 
	 * @param value the syncRate - Number of hours between syncs of product feed.
	 */
	public void setSyncRate(final EnumerationValue value)
	{
		setSyncRate( getSession().getSessionContext(), value );
	}
	
}
