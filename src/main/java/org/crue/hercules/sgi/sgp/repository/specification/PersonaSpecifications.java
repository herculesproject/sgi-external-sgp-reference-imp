package org.crue.hercules.sgi.sgp.repository.specification;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.lang3.StringUtils;
import org.crue.hercules.sgi.sgp.enums.TipoColectivo;
import org.crue.hercules.sgi.sgp.exceptions.TipoColectivoNotFoundException;
import org.crue.hercules.sgi.sgp.model.ColectivoTipoColectivo;
import org.crue.hercules.sgi.sgp.model.ColectivoTipoColectivo_;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.model.Persona_;
import org.springframework.data.jpa.domain.Specification;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonaSpecifications {

  private static final String PERCENT_SIGN = "%";
  private static final String LIST_SEPARATOR = ",";

  /**
   * {@link Persona} que pertenezcan a alguno de los colectivos de la lista.
   * 
   * @param colectivoIds lista de indentificadores de colectivos separados por
   *                     ","
   * @return specification para obtener los {@link Persona} que cumplan con el
   *         filtro de busqueda.
   */
  public static Specification<Persona> byColectivoIds(String colectivoIds) {
    List<String> colectivos = Arrays.asList(colectivoIds.replaceAll("[\\(\\)]", "").split(LIST_SEPARATOR)).stream()
        .collect(Collectors.toList());

    return byColectivoIds(colectivos);
  }

  /**
   * {@link Persona} que pertenezcan a alguno de los colectivos de la lista.
   * 
   * @param colectivoIds lista de indentificadores de colectivos
   * @return specification para obtener los {@link Persona} que cumplan con el
   *         filtro de busqueda.
   */
  public static Specification<Persona> byColectivoIds(List<String> colectivoIds) {
    return (root, query, cb) -> root.get(Persona_.colectivoId).in(colectivoIds);
  }

  /**
   * {@link Persona} que contengan el texto de busqueda en el email
   * 
   * @param filter texto para buscar en el email
   * @return specification para obtener los {@link Persona} que cumplan con el
   *         filtro de busqueda.
   */
  public static Specification<Persona> byEmail(String filter) {
    return (root, query, cb) -> cb.like(cb.lower(root.get(Persona_.email)),
        PERCENT_SIGN + filter.toLowerCase() + PERCENT_SIGN);
  }

  /**
   * {@link Persona} que contengan el texto de busqueda en la concatenacion del
   * nombre y los apellidos.
   * 
   * @param filter texto para buscar en el nombre y apellidos
   * @return specification para obtener los {@link Persona} que cumplan con el
   *         filtro de busqueda.
   */
  public static Specification<Persona> byNombreAndApellidos(String filter) {
    return (root, query, cb) -> {
      Expression<String> nombreApellidos = cb.concat(
          cb.concat(root.get(Persona_.nombre), StringUtils.SPACE),
          root.get(Persona_.apellidos));

      return cb.like(cb.lower(nombreApellidos), PERCENT_SIGN + filter.toLowerCase() + PERCENT_SIGN);
    };
  }

  /**
   * {@link Persona} que contengan el texto de busqueda en la concatenacion del
   * nombre, apellidos y emails.
   * 
   * @param filter texto para buscar en el nombre, apellidos y emails
   * @return specification para obtener los {@link Persona} que cumplan con el
   *         filtro de busqueda.
   */
  public static Specification<Persona> byNombreAndApellidosAndEmails(String filter) {
    return (root, query, cb) -> {
      Expression<String> nombreAndApellidosAndEmails = cb.concat(
          cb.concat(cb.concat(root.get(Persona_.nombre), StringUtils.SPACE),
              cb.concat(root.get(Persona_.apellidos), StringUtils.SPACE)),
          root.get(Persona_.email));

      return cb.like(cb.lower(nombreAndApellidosAndEmails), PERCENT_SIGN + filter.toLowerCase() + PERCENT_SIGN);
    };
  }

  /**
   * {@link Persona} que contengan el texto de busqueda en la concatenacion del
   * nombre, apellidos y emails.
   * 
   * @param tipoColectivo {@link TipoColectivo}
   * @return specification para obtener los {@link Persona} que cumplan con el
   *         filtro de busqueda.
   */
  public static Specification<Persona> byTipoColectivo(String tipoColectivo) {
    return (root, query, cb) -> {

      TipoColectivo tipoColectivoEnum = TipoColectivo.fromCode(tipoColectivo);

      if (tipoColectivoEnum == null) {
        throw new TipoColectivoNotFoundException(tipoColectivo);
      }

      Subquery<String> queryColectivos = query.subquery(String.class);
      Root<ColectivoTipoColectivo> subqRoot = queryColectivos.from(ColectivoTipoColectivo.class);
      queryColectivos.select(subqRoot.get(ColectivoTipoColectivo_.colectivoId))
          .where(cb.equal(subqRoot.get(ColectivoTipoColectivo_.tipoColectivo), tipoColectivoEnum));
      return root.get(Persona_.colectivoId).in(queryColectivos);
    };
  }

}
