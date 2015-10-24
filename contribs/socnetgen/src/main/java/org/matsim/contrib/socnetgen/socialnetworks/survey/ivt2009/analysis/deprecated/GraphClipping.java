/* *********************************************************************** *
 * project: org.matsim.*
 * GraphClipping.java
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

import com.vividsolutions.jts.geom.Geometry;
import org.matsim.contrib.common.gis.EsriShapeIO;
import org.matsim.contrib.socnetgen.sna.graph.analysis.GraphFilter;
import org.matsim.contrib.socnetgen.sna.graph.social.io.SocialGraphMLWriter;
import org.matsim.contrib.socnetgen.sna.graph.spatial.SpatialGraph;
import org.matsim.contrib.socnetgen.sna.graph.spatial.analysis.SpatialFilter;
import org.matsim.contrib.socnetgen.sna.snowball.SampledGraphProjection;
import org.matsim.contrib.socnetgen.sna.snowball.SampledGraphProjectionBuilder;
import org.matsim.contrib.socnetgen.sna.snowball.io.SampledGraphProjMLReader;
import org.matsim.contrib.socnetgen.sna.snowball.io.SampledGraphProjMLWriter;
import org.matsim.contrib.socnetgen.sna.snowball.social.SocialSampledGraphProjectionBuilder;
import org.matsim.contrib.socnetgen.socialnetworks.survey.ivt2009.graph.SocialSparseEdge;
import org.matsim.contrib.socnetgen.socialnetworks.survey.ivt2009.graph.SocialSparseGraph;
import org.matsim.contrib.socnetgen.socialnetworks.survey.ivt2009.graph.SocialSparseGraphBuilder;
import org.matsim.contrib.socnetgen.socialnetworks.survey.ivt2009.graph.SocialSparseVertex;
import org.matsim.contrib.socnetgen.socialnetworks.survey.ivt2009.graph.io.SocialSparseGraphMLReader;
import org.opengis.feature.simple.SimpleFeature;

import java.io.IOException;

/**
 * @author illenberger
 *
 */
public class GraphClipping {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		SampledGraphProjMLReader<SocialSparseGraph, SocialSparseVertex, SocialSparseEdge> reader =
			new SampledGraphProjMLReader<SocialSparseGraph, SocialSparseVertex, SocialSparseEdge>(new SocialSparseGraphMLReader());
		
		reader.setGraphProjectionBuilder(new SocialSampledGraphProjectionBuilder<SocialSparseGraph, SocialSparseVertex, SocialSparseEdge>());
		
		SampledGraphProjection<SocialSparseGraph, SocialSparseVertex, SocialSparseEdge> graph = reader.readGraph(args[0]);
		
		SimpleFeature feature = EsriShapeIO.readFeatures("/Users/jillenberger/Work/work/socialnets/data/schweiz/complete/zones/G1L08.shp").iterator().next();
		Geometry geometry = (Geometry) feature.getDefaultGeometry();
		geometry.setSRID(21781);
		GraphFilter<SpatialGraph> filter = new SpatialFilter(new SocialSparseGraphBuilder(graph.getDelegate().getCoordinateReferenceSysten()), geometry);
		filter.apply(graph.getDelegate());
		SampledGraphProjectionBuilder<SocialSparseGraph, SocialSparseVertex, SocialSparseEdge> builder = new SampledGraphProjectionBuilder<SocialSparseGraph, SocialSparseVertex, SocialSparseEdge>();
		builder.synchronize(graph);

		SampledGraphProjMLWriter writer = new SampledGraphProjMLWriter(new SocialGraphMLWriter());
		writer.write(graph, args[1]);
	}

}
