package com.innovista.db.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(FIELD)
@Retention(RUNTIME)
public @interface Password {

    String baseDirectory() default "";

    /**
     * this inner annotaion can be used in both 
     * @FileReference and @Lob annotated fields
     * 
     */
    @Target(FIELD)
    @Retention(RUNTIME)
    public @interface Constraints {
        /**
         * allowed extensions (comma separated or *)
         *
         * @return comma separated extensions
         */
        String value() default "jpg,jpeg,png";
        
        /**
         * file size limit in megabytes
         *
         * @return file size limit in megabytes
         */
        int limit() default 10;
    }
}
