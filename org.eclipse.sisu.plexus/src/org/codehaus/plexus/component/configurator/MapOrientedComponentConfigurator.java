/*******************************************************************************
 * Copyright (c) 2010, 2012 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stuart McCulloch (Sonatype, Inc.) - initial API and implementation
 *******************************************************************************/
package org.codehaus.plexus.component.configurator;

import java.util.Map;

import org.codehaus.plexus.classworlds.realm.ClassRealm;
import org.codehaus.plexus.component.MapOrientedComponent;
import org.codehaus.plexus.component.configurator.converters.composite.MapConverter;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluator;
import org.codehaus.plexus.configuration.PlexusConfiguration;

public class MapOrientedComponentConfigurator
    extends AbstractComponentConfigurator
{
    @Override
    public void configureComponent( final Object component, final PlexusConfiguration configuration,
                                    final ExpressionEvaluator evaluator, final ClassRealm realm,
                                    final ConfigurationListener listener )
        throws ComponentConfigurationException
    {
        if ( component instanceof MapOrientedComponent )
        {
            ( (MapOrientedComponent) component ).setComponentConfiguration(
            /* <--- */(Map<?, ?>) new MapConverter().fromConfiguration( converterLookup, configuration, Map.class,
                                                                        component.getClass(), realm, evaluator,
                                                                        listener ) );
        }
        else
        {
            throw new ComponentConfigurationException( "Component does not implement " + MapOrientedComponent.class );
        }
    }
}
