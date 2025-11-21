package org.crue.hercules.sgi.sgp.service.sgi;

import org.crue.hercules.sgi.sgp.config.RestApiProperties;
import org.crue.hercules.sgi.sgp.dto.cnf.ConfigOutput;
import org.crue.hercules.sgi.sgp.enums.ServiceType;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SgiCnfService extends SgiApiBaseService {

  private static final String PATH_DELIMITER = "/";
  private static final String PATH_CONFIG = PATH_DELIMITER + "config";
  private static final String PATH_NAME = PATH_DELIMITER + "{name}";
  private static final String PATH_CONFIG_BY_NAME = PATH_CONFIG + PATH_NAME;

  private static final String ENTIDAD_PROPIA_REF_CONFIG_NAME = "id-entidad-sgemp";

  private final ServiceType serviceType;

  public SgiCnfService(RestApiProperties restApiProperties, RestTemplate restTemplate) {
    super(restApiProperties, restTemplate);
    this.serviceType = ServiceType.CNF;
  }

  /**
   * Devuelve el identificador de la entidad propia de la configuracion
   * 
   * @return el identificador de la entidad propia
   */
  public String getEntidadPropiaRef() {
    log.debug("getEntidadPropiaRef() - start");
    String entidadPropiaRef = getConfig(ENTIDAD_PROPIA_REF_CONFIG_NAME).getValue();
    log.debug("getEntidadPropiaRef() - end");
    return entidadPropiaRef;
  }

  /**
   * Devuelve un valor de configuracion
   *
   * @param name nombre de la configruacion
   * @return el valor de configuracion
   */
  public ConfigOutput getConfig(String name) {
    log.debug("getConfig({}) - start", name);
    ConfigOutput config = null;
    try {
      HttpMethod httpMethod = HttpMethod.GET;
      String mergedURL = buildUri(serviceType, PATH_CONFIG_BY_NAME);

      config = super.callEndpoint(mergedURL, httpMethod,
          new ParameterizedTypeReference<ConfigOutput>() {
          }, name).getBody();
      log.debug("getConfig({}) - end", name);
      return config;
    } catch (Exception e) {
      log.error("getConfig({}) - error: ", name, e.getMessage(), e);
      throw e;
    }

  }

  public String getServiceBaseURL() {
    return super.getServiceBaseURL(serviceType);
  }

}
