/* *********************************************************************** *
 * project: org.matsim.*
 * Income.java
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
package org.matsim.contrib.socnetgen.socialnetworks.survey.ivt2009.analysis.deprecated;

import org.matsim.contrib.socnetgen.sna.graph.social.SocialVertex;

/**
 * @author illenberger
 *
 */
public class Income extends SocioMatrixLegacy {

	@Override
	protected String getAttributeValue(SocialVertex vertex) {
		return String.valueOf(vertex.getPerson().getIncome());
	}

}
