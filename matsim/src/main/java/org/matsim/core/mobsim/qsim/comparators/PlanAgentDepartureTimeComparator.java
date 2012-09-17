/* *********************************************************************** *
 * project: org.matsim.*
 * DriverAgentDepartureTimeComparator.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2009 by the members listed in the COPYING,        *
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

package org.matsim.core.mobsim.qsim.comparators;

import java.io.Serializable;
import java.util.Comparator;

import org.matsim.core.api.internal.MatsimComparator;
import org.matsim.core.mobsim.framework.MobsimAgent;
import org.matsim.core.mobsim.framework.MobsimDriverAgent;

/**
 * Compares two {@link MobsimDriverAgent}s according to their (planned) departure time. If the 
 * departure times are the same, the agent with the higher id is considered smaller.
 *
 * @author mrieser
 * 
 * @see MobsimDriverAgent#getActivityEndTime()
 */
public class PlanAgentDepartureTimeComparator implements Comparator<MobsimAgent>, Serializable, MatsimComparator {

	private static final long serialVersionUID = 1L;

	@Override
	public int compare(MobsimAgent agent1, MobsimAgent agent2) {
		int cmp = Double.compare(agent1.getActivityEndTime(), agent2.getActivityEndTime());
		if (cmp == 0) {
			// Both depart at the same time -> let the one with the larger id be first (=smaller)
			return agent2.getId().compareTo(agent1.getId());
		}
		return cmp;
	}

}
