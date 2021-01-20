package com.epam.web.entity;

import java.io.Serializable;

/**
 * The {@code Entity} interface marker for controlling entities in data access object layers
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public interface Entity extends Serializable, Cloneable {

    /**
     * Getting id for working with entities in data access object
     *
     */
    Long getId();
}
