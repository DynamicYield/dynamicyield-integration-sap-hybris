package com.dynamic.yield.dao.impl;

import com.dynamic.yield.dao.DynamicYieldIntegrationDao;
import com.dynamic.yield.model.DynamicYieldIntegrationModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.List;

public class DefaultDynamicYieldIntegrationDao extends DefaultGenericDao<DynamicYieldIntegrationModel>
        implements DynamicYieldIntegrationDao {

    public DefaultDynamicYieldIntegrationDao() {
        super(DynamicYieldIntegrationModel._TYPECODE);
    }

    @Override
    public DynamicYieldIntegrationModel getMainDynamicYieldConfiguration() {
        final List<DynamicYieldIntegrationModel> dyIntegration = this.find();
        return dyIntegration.isEmpty() ? null : dyIntegration.get(0);
    }
}
