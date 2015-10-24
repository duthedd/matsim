/* *********************************************************************** *
 * project: org.matsim.*
 * DistanceAge.java
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

import gnu.trove.TDoubleObjectHashMap;
import gnu.trove.TDoubleObjectIterator;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.matsim.contrib.common.stats.Discretizer;
import org.matsim.contrib.common.stats.LinearDiscretizer;
import org.matsim.contrib.socnetgen.sna.graph.Graph;
import org.matsim.contrib.socnetgen.sna.graph.analysis.AnalyzerTask;
import org.matsim.contrib.socnetgen.sna.graph.social.SocialGraph;
import org.matsim.contrib.socnetgen.sna.graph.social.SocialVertex;
import org.matsim.contrib.socnetgen.sna.graph.spatial.analysis.Distance;
import org.matsim.contrib.socnetgen.sna.snowball.SampledVertex;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author illenberger
 * 
 */
public class DistanceAge extends AnalyzerTask {

	@Override
	public void analyze(Graph graph, Map<String, DescriptiveStatistics> statsMap) {
		TDoubleObjectHashMap<Set<SocialVertex>> partitions = new TDoubleObjectHashMap<Set<SocialVertex>>();

		Discretizer discr = new LinearDiscretizer(5.0);

		SocialGraph g = (SocialGraph) graph;
		for (SocialVertex vertex : g.getVertices()) {
			if (((SampledVertex) vertex).isSampled()) {
				double age = discr.discretize(vertex.getPerson().getAge());
				Set<SocialVertex> partition = partitions.get(age);
				if (partition == null) {
					partition = new HashSet<SocialVertex>();
					partitions.put(age, partition);
				}
				partition.add(vertex);
			}
		}

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(getOutputDirectory() + "/d_age.txt"));
			writer.write("age\tdist");
			writer.newLine();
			
			Distance dist = new Distance();
			TDoubleObjectIterator<Set<SocialVertex>> it = partitions.iterator();
			for (int i = 0; i < partitions.size(); i++) {
				it.advance();
				double mean = dist.vertexMeanDistribution(it.value()).mean();
			
				writer.write(String.valueOf(it.key()));
				writer.write("\t");
				writer.write(String.valueOf(mean));
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
