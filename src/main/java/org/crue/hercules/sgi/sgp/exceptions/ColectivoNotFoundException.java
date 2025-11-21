package org.crue.hercules.sgi.sgp.exceptions;

import org.crue.hercules.sgi.sgp.model.Colectivo;

/**
 * ColectivoNotFoundException
 */
public class ColectivoNotFoundException extends SgpNotFoundException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public ColectivoNotFoundException(String id) {
    super(id, Colectivo.class);
  }

}
