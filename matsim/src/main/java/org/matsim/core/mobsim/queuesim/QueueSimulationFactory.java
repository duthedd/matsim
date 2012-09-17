/* *********************************************************************** *
 * project: org.matsim.*
 * QueueSimulationFactory
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2010 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */
package org.matsim.core.mobsim.queuesim;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.mobsim.framework.Mobsim;
import org.matsim.core.mobsim.framework.MobsimFactory;

/**
 * @author dgrether
 *
 */
public class QueueSimulationFactory implements MobsimFactory {

	@Deprecated // use (new QueueSimulationFactory()).createMobsim(...) instead.  kai, may'10
	public static QueueSimulation createMobsimStatic(Scenario scenario, EventsManager events) {
		// this was introduced so I could to automatic refactoring to protect the constructor.  kai, may'10
		return (QueueSimulation) (new QueueSimulationFactory()).createMobsim( scenario, events) ;
	}

	@Override
	public Mobsim createMobsim(Scenario sc, EventsManager eventsManager) {
		return new QueueSimulation( sc, eventsManager );
	}

}
