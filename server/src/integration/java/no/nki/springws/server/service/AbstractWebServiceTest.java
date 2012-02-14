package no.nki.springws.server.service;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
 
@ContextConfiguration(locations = { "classpath:spring-integration-services.xml", "classpath:spring-integration-properties.xml", "classpath:spring-cxf.xml", "classpath:spring-integration-jaxws-endpoints.xml", "classpath:user-account-server.xml", "classpath:user-account-services.xml", "classpath:log4j.xml" })
public abstract class AbstractWebServiceTest extends AbstractJUnit4SpringContextTests {

}