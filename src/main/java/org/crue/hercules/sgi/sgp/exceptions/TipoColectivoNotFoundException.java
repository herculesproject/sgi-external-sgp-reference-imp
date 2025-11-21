package org.crue.hercules.sgi.sgp.exceptions;

import org.crue.hercules.sgi.sgp.enums.TipoColectivo;

/**
 * TipoColectivoNotFoundException
 */
public class TipoColectivoNotFoundException extends SgpNotFoundException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public TipoColectivoNotFoundException(String id) {
    super(id, TipoColectivo.class);
  }

}
