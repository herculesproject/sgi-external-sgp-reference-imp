package org.crue.hercules.sgi.sgp.repository.predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.crue.hercules.sgi.framework.rsql.SgiRSQLPredicateResolver;
import org.crue.hercules.sgi.sgp.model.HistoricoCategoriaProfesional;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoricoCategoriaProfesionalPredicateResolver
    implements SgiRSQLPredicateResolver<HistoricoCategoriaProfesional> {

  private enum Property {
    FECHA_FIN("fechaFin");

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

  private static HistoricoCategoriaProfesionalPredicateResolver instance;

  public static HistoricoCategoriaProfesionalPredicateResolver getInstance() {
    if (instance == null) {
      instance = new HistoricoCategoriaProfesionalPredicateResolver();
    }
    return instance;
  }

  @Override
  public boolean isManaged(ComparisonNode node) {
    Property property = Property.fromCode(node.getSelector());
    return property != null;

  }

  @Override
  public Predicate toPredicate(ComparisonNode node, Root<HistoricoCategoriaProfesional> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {

    Property property = Property.fromCode(node.getSelector());
    if (property == null) {
      return null;
    }

    if (property == Property.FECHA_FIN) {
      return buildByFechaFin(criteriaBuilder);
    } else {
      return null;
    }

  }

  private static Predicate buildByFechaFin(CriteriaBuilder cb) {
    // No hace ningun filtrado
    return cb.isTrue(cb.literal(true));
  }

}
