package com.powerup.common.app;

import org.springframework.boot.SpringApplication;

/**
 * The <code>SpringBootApplicationAbs</code> class is the parent class to use for spring boot applications.
 *
 * @author Chris Picard
 */
public abstract class SpringBootApplicationAbs extends SpringApplication {

    /**
     * Base constructor.
     *
     * @param childClass The class of the child spring boot class.
     */
    public SpringBootApplicationAbs(final Class<?> childClass) {
        super(childClass);
    }
}