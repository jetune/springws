package no.nki.springws.server.service;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:spring-services.xml", "classpath:spring-cxf.xml", "classpath:spring-jaxws-endpoints.xml", "classpath:log4j.xml" })
public abstract class AbstractWebServiceTest extends AbstractJUnit4SpringContextTests {

}