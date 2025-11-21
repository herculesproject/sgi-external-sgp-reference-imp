package org.crue.hercules.sgi.sgp.repository.predicate;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.crue.hercules.sgi.framework.rsql.SgiRSQLPredicateResolver;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.crue.hercules.sgi.sgp.repository.specification.PersonaSpecifications;
import org.crue.hercules.sgi.sgp.util.PredicateResolverUtil;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import io.github.perplexhub.rsql.RSQLOperators;

public class PersonaPredicateResolver implements SgiRSQLPredicateResolver<Persona> {

  private enum Property {
    BUSQUEDA("busqueda"),
    COLECTIVO_ID("colectivoId"),
    NOMBRE_APELLIDOS("nombreApellidos"),
    TIPO_COLECTIVO("tipoColectivo"),
    EMAIL("email");

    private String code;

    private Property(String code) {
      this.code = code;
    }

    public static Property fromCode(String code) {
      for (Property property : Property.values()) {
        if (property.code.equals(code)) {
          return property;
        }
      }
      return null;
    }
  }

  private static PersonaPredicateResolver instance;

  private PersonaPredicateResolver() {
    // Do nothing. Hide external instanciation
  }

  public static PersonaPredicateResolver getInstance() {
    if (instance == null) {
      instance = new PersonaPredicateResolver();
    }
    return instance;
  }

  @Override
  public boolean isManaged(ComparisonNode node) {
    Property property = Property.fromCode(node.getSelector());
    return property != null;

  }

  @Override
  public Predicate toPredicate(ComparisonNode node, Root<Persona> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {

    Property property = Property.fromCode(node.getSelector());
    if (property == null) {
      return null;
    }

    switch (property) {
      case BUSQUEDA:
        return buildByBusqueda(node, root, query, criteriaBuilder);
      case COLECTIVO_ID:
        return buildByColectivoId(node, root, query, criteriaBuilder);
      case EMAIL:
        return buildByEmail(node, root, query, criteriaBuilder);
      case NOMBRE_APELLIDOS:
        return buildByNombreApellidos(node, root, query, criteriaBuilder);
      case TIPO_COLECTIVO:
        return buildByTipoColectivo(node, root, query, criteriaBuilder);
      default:
        return null;
    }
  }

  private static Predicate buildByBusqueda(ComparisonNode node, Root<Persona> root, CriteriaQuery<?> query,
      CriteriaBuilder cb) {
    PredicateResolverUtil.validateOperatorIsSupported(node, RSQLOperators.IGNORE_CASE_LIKE);
    PredicateResolverUtil.validateOperatorArgumentNumber(node, 1);

    String filter = node.getArguments().get(0);

    return PersonaSpecifications.byNombreAndApellidosAndEmails(filter).toPredicate(root, query, cb);
  }

  private static Predicate buildByEmail(ComparisonNode node, Root<Persona> root, CriteriaQuery<?> query,
      CriteriaBuilder cb) {
    PredicateResolverUtil.validateOperatorIsSupported(node, RSQLOperators.IGNORE_CASE_LIKE);
    PredicateResolverUtil.validateOperatorArgumentNumber(node, 1);

    String filter = node.getArguments().get(0);

    return PersonaSpecifications.byEmail(filter).toPredicate(root, query, cb);
  }

  private static Predicate buildByColectivoId(ComparisonNode node, Root<Persona> root, CriteriaQuery<?> query,
      CriteriaBuilder cb) {
    PredicateResolverUtil.validateOperatorIsSupported(node, RSQLOperators.EQUAL, RSQLOperators.IN);

    if (node.getOperator().equals(RSQLOperators.EQUAL)) {
      PredicateResolverUtil.validateOperatorArgumentNumber(node, 1);
    }

    List<String> filter = node.getArguments();

    return PersonaSpecifications.byColectivoIds(filter).toPredicate(root, query, cb);
  }

  private static Predicate buildByNombreApellidos(ComparisonNode node, Root<Persona> root, CriteriaQuery<?> query,
      CriteriaBuilder cb) {
    PredicateResolverUtil.validateOperatorIsSupported(node, RSQLOperators.IGNORE_CASE_LIKE);
    PredicateResolverUtil.validateOperatorArgumentNumber(node, 1);

    String filter = node.getArguments().get(0);

    return PersonaSpecifications.byNombreAndApellidos(filter).toPredicate(root, query, cb);
  }

  private static Predicate buildByTipoColectivo(ComparisonNode node, Root<Persona> root, CriteriaQuery<?> query,
      CriteriaBuilder cb) {
    PredicateResolverUtil.validateOperatorIsSupported(node, RSQLOperators.EQUAL);
    PredicateResolverUtil.validateOperatorArgumentNumber(node, 1);

    String tipoColectivo = node.getArguments().get(0);

    return PersonaSpecifications.byTipoColectivo(tipoColectivo).toPredicate(root, query, cb);
  }

}
