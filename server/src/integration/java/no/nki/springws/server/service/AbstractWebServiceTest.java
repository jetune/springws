package no.nki.springws.server.service;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
 
@ContextConfiguration(locations = { "classpath:spring-test-services.xml", "classpath:spring-test-properties.xml", "classpath:spring-cxf.xml", "classpath:spring-test-jaxws-endpoints.xml", "classpath:spring-test-cxf-security.xml", "classpath:log4j.xml" })
public abstract class AbstractWebServiceTest extends AbstractJUnit4SpringContextTests {

}