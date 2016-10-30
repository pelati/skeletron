package com.jami.base;

import com.jami.json.JsonUtils;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBException;
import java.net.URISyntaxException;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author jonp
 */
public abstract class TestFacadeBase extends TestBase {
    @Mock
    protected static HttpServletRequest httpServletRequest;

    private Dispatcher dispatcher;

    protected abstract Object facadeInstance();

    private Dispatcher createDispatcher() {
        Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
        dispatcher.getRegistry().addSingletonResource(facadeInstance());

        ResteasyProviderFactory
                .getContextDataMap()
                .put(HttpServletRequest.class, httpServletRequest);

        // register providers
        for (Class<?> clazz : applicationInstance().getClasses()) {
            dispatcher.getProviderFactory().register(clazz);
        }

        return dispatcher;
    }

    @Before
    public void setUpBaseFacadeTest() throws Exception {
        initMocks(this);
        dispatcher = createDispatcher();
        given(httpServletRequest.getRemoteAddr()).willReturn("127.0.0.1");
        given(httpServletRequest.getHeader("User-Agent")).willReturn("JunitMockedRequest");
    }

    @After
    public void tearDownBaseFacadeTest() throws Exception {
    }

    protected Application applicationInstance() {
        return new Application();
    }

    public MockHttpResponse sendPostRequest(
            String path,
            Map<String, String> pathParams,
            Map<String, String> queryParams,
            Map<String, String> headerParams,
            Object requestBody) throws URISyntaxException, JAXBException {
        if (requestBody == null) {
            // Must no be null
            requestBody = new byte[0];
        }
        String uri = UriBuilder.fromPath(path).buildFromMap(pathParams).toString();
        MockHttpRequest request = MockHttpRequest.post(uri)
                .header("Content-type", MediaType.APPLICATION_JSON)
                .content(JsonUtils.serialize(requestBody).getBytes(UTF_8));
        return sendHttpRequest(request, headerParams, queryParams);
    }

    public MockHttpResponse sendPutRequest(
            String path,
            Map<String, String> pathParams,
            Map<String, String> queryParams,
            Map<String, String> headerParams,
            Object requestBody) throws URISyntaxException, JAXBException {
        if (requestBody == null) {
            // Must no be null
            requestBody = new byte[0];
        }
        String uri = UriBuilder.fromPath(path).buildFromMap(pathParams).toString();
        MockHttpRequest request = MockHttpRequest.put(uri)
                .header("Content-type", MediaType.APPLICATION_JSON)
                .content(JsonUtils.serialize(requestBody).getBytes(UTF_8));
        return sendHttpRequest(request, headerParams, queryParams);
    }

    public MockHttpResponse sendGetRequest(
            String path,
            Map<String, String> pathParams,
            Map<String, String> queryParams,
            Map<String, String> headerParams)
            throws URISyntaxException, JAXBException {
        String uri = UriBuilder.fromPath(path).buildFromMap(pathParams).toString();
        MockHttpRequest request = MockHttpRequest.get(uri)
                .header("Content-type", MediaType.APPLICATION_JSON);
        return sendHttpRequest(request, headerParams, queryParams);
    }

    /**
     * Sends a HTTP request.
     */
    private MockHttpResponse sendHttpRequest(MockHttpRequest request, Map<String, String> headerParams, Map<String, String> queryParams) throws URISyntaxException {
        MockHttpResponse response = new MockHttpResponse();

        // Add possible query params
        if (queryParams != null) {
            for (String key : queryParams.keySet()) {
                request.getUri().getQueryParameters().add(key, queryParams.get(key));
            }
        }

        // Add possible path params
        if (headerParams != null) {
            for (String header : headerParams.keySet()) {
                given(httpServletRequest.getHeader(header)).willReturn(headerParams.get(header));
            }
        }

        dispatcher.invoke(request, response);
        assertThat(response.getStatus()).isEqualTo(200);
        return response;
    }
}
