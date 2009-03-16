/* *********************************************************************** *
 * project: org.matsim.*
 * MergePlans.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2007 by the members listed in the COPYING,        *
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

package playground.yu.newPlans;

import org.matsim.config.Config;
import org.matsim.gbl.Gbl;
import org.matsim.interfaces.core.v01.Person;
import org.matsim.interfaces.core.v01.Population;
import org.matsim.network.MatsimNetworkReader;
import org.matsim.network.NetworkLayer;
import org.matsim.population.MatsimPopulationReader;
import org.matsim.population.PopulationImpl;
import org.matsim.population.PopulationWriter;
import org.matsim.population.algorithms.AbstractPersonAlgorithm;

/**
 * @author ychen
 * 
 */
public class MergePlans {
	public static class CopyPlans extends AbstractPersonAlgorithm {
		private final PopulationWriter writer;

		public CopyPlans(final PopulationWriter writer) {
			this.writer = writer;
		}

		@Override
		public void run(final Person person) {
			this.writer.writePerson(person);
		}
	}

	private static final class PersonIdCopyPlans extends CopyPlans {
		private final int lower_limit;

		public PersonIdCopyPlans(final PopulationWriter writer,
				final int lower_limit) {
			super(writer);
			this.lower_limit = lower_limit;
		}

		@Override
		public void run(final Person person) {
			if (Integer.parseInt(person.getId().toString()) >= this.lower_limit)
				super.run(person);
		}
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final String path = "../data/ivtch/input/";
		final String netFilename = path + "ivtch-osm.xml";
		final String plansFilenameA = path + "plans_all_zrh30km_100pct.xml.gz";
		final String plansFilenameB = path
				+ "plans_miv_zrh30km_transitincl_100pct_not_direct_2_use.xml.gz";
		final String outputPlansFilename = path
				+ "plans_all_zrh30km_transitincl_100pct.xml.gz";

		// final String path = "test/yu/equil_test/";
		// final String netFilename = path + "equil_net.xml";
		// final String plansFilenameA = path + "plans100pt.xml";
		// final String plansFilenameB = path + "plans300.xml";
		// final String outputPlansFilename = path +
		// "sum_plans_100pt_201-300.xml";

		final int lower_limit = 1000000000;
		Config config = Gbl.createConfig(null);
		config.plans().setOutputFile(outputPlansFilename);
		config.plans().switchOffPlansStreaming(false);

		NetworkLayer network = new NetworkLayer();
		new MatsimNetworkReader(network).readFile(netFilename);

		Population plansA = new PopulationImpl();
		PopulationWriter pw = new PopulationWriter(plansA);
		new MatsimPopulationReader(plansA, network).readFile(plansFilenameA);
		new CopyPlans(pw).run(plansA);

		Population plansB = new PopulationImpl();
		new MatsimPopulationReader(plansB, network).readFile(plansFilenameB);
		new PersonIdCopyPlans(pw, lower_limit).run(plansB);
		pw.write();
	}
}
