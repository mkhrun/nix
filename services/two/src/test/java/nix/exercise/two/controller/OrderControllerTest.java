package nix.exercise.two.controller;

import java.math.BigDecimal;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static java.math.BigDecimal.TEN;
import static java.util.Collections.singletonList;
import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EurekaClient eurekaClient;
    @MockBean
    private RestTemplate restTemplate;
    @MockBean
    private Application application;
    @MockBean
    private InstanceInfo instanceInfo;
    @MockBean
    private UriComponentsBuilder builder;
    @MockBean
    private UriComponents uriComponents;

    @Test
    public void addOrder_InactivePhoneCatalogService() throws Exception {
        when(eurekaClient.getApplication(anyString())).thenReturn(null);

        mockMvc.perform(post("/order/new"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.reason", equalTo("Service is down")));

        verifyZeroInteractions(restTemplate);
    }

    @Test
    public void addOrder_ValidPhoneArticle_ExpectedSum() throws Exception {
        when(eurekaClient.getApplication(anyString())).thenReturn(application);
        when(application.getInstances()).thenReturn(singletonList(instanceInfo));
        when(builder.build()).thenReturn(uriComponents);
        when(uriComponents.toString()).thenReturn(EMPTY);

        Mockito.<ResponseEntity<BigDecimal>>when(restTemplate.getForEntity(anyString(), any())).thenReturn(ok(TEN));

        mockMvc.perform(post("/order/new").param("phones[0].article", "aaa"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.totalPrice").value(TEN.toString()));
    }

    @Test
    public void addOrder_InvalidPhoneArticle() throws Exception {
        when(eurekaClient.getApplication(anyString())).thenReturn(application);
        when(application.getInstances()).thenReturn(singletonList(instanceInfo));
        when(builder.build()).thenReturn(uriComponents);
        when(uriComponents.toString()).thenReturn(EMPTY);

        when(restTemplate.getForEntity(anyString(), any())).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(post("/order/new").param("phones[0].article", "aaa"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.reason", equalTo("Order contains not valid phone")));
    }
}
