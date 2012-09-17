/* *********************************************************************** *
 * project: org.matsim.*
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


package org.matsim.signalsystems.data.signalgroups.v20;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.matsim.api.core.v01.Id;
import org.matsim.core.basic.v01.IdImpl;
import org.matsim.signalsystems.MatsimSignalSystemsReader;
import org.matsim.testcases.MatsimTestUtils;
import org.xml.sax.SAXException;

/**
* @author jbischoff
* @author dgrether
* 
*/
public class SignalGroups20ReaderWriterTest {
	private static final Logger log = Logger.getLogger(SignalGroups20ReaderWriterTest.class);

	private static final String TESTXML = "testSignalGroups_v2.0.xml";

	@Rule
	public MatsimTestUtils testUtils = new MatsimTestUtils();

	private Id id23 = new IdImpl("23");
	private Id id1 = new IdImpl("1");
	private Id id2 = new IdImpl("2");
	private Id id4 = new IdImpl("4");
	private Id id5 = new IdImpl("5");
	private Id id42 = new IdImpl("42");
	Set<Id> sg;





	@Test
	public void testParser() throws IOException, JAXBException, SAXException,
			ParserConfigurationException {
		SignalGroupsData sgd = new SignalGroupsDataImpl();
		SignalGroupsReader20 reader = new SignalGroupsReader20(sgd,MatsimSignalSystemsReader.SIGNALGROUPS20);
		reader.readFile(this.testUtils.getPackageInputDirectory() + TESTXML);
		checkContent(sgd);

	}
	
	@Test
	public void testWriter() throws JAXBException, SAXException, ParserConfigurationException,
			IOException {
		String testoutput = this.testUtils.getOutputDirectory() + "testSgOutput.xml";
		log.debug("reading file...");
		// read the test file
		SignalGroupsData sgd = new SignalGroupsDataImpl();
		SignalGroupsReader20 reader = new SignalGroupsReader20(sgd, MatsimSignalSystemsReader.SIGNALGROUPS20);
		reader.readFile(this.testUtils.getPackageInputDirectory() + TESTXML);

		// write the test file
		log.debug("write the test file...");
		SignalGroupsWriter20 writer = new SignalGroupsWriter20(sgd);
		writer.write(testoutput);

		log.debug("and read it again");
		sgd = new SignalGroupsDataImpl();
		reader = new SignalGroupsReader20(sgd, MatsimSignalSystemsReader.SIGNALGROUPS20);
		reader.readFile(testoutput);
		checkContent(sgd);
	}
	

	
	private void checkContent(SignalGroupsData sgd) {
		Assert.assertNotNull(sgd);
		Assert.assertNotNull(sgd.getSignalGroupDataBySignalSystemId());
		Assert.assertNotNull(sgd.getSignalGroupDataBySystemId(id23));
		
		//sg23
		Map<Id,SignalGroupData> ss23 = sgd.getSignalGroupDataBySystemId(id23);
		Assert.assertEquals(id23,ss23.get(id1).getSignalSystemId());

		sg =  ss23.get(id1).getSignalIds();
		Assert.assertTrue(sg.contains(id1));
		
		//sg42
		Assert.assertNotNull(sgd.getSignalGroupDataBySystemId(id42));
		Map<Id,SignalGroupData> ss42 = sgd.getSignalGroupDataBySystemId(id42);
		Assert.assertEquals(id42,ss42.get(id1).getSignalSystemId());

		sg =  ss42.get(id1).getSignalIds();
		Assert.assertTrue(sg.contains(id1));
		sg =  ss42.get(id2).getSignalIds();
		Assert.assertTrue(sg.contains(id1));
		Assert.assertTrue(sg.contains(id4));
		Assert.assertTrue(sg.contains(id5));
		

		
		
	
		}
		
		
	}
	
	
	
	

