package axblount.packagelocal;

import java.lang.annotation.*;
import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;

/**
 * This annotation marks a constructor, field,
 * method, class, interface, enum, or annotation as
 * local to its package.
 *
 * This annotation is identical to {@link PackageLocal},
 * but in the "keyword style" for inline use.
 *
 * @see axblount.packagelocal.PackageLocal
 */
@Retention(SOURCE)
@Target({CONSTRUCTOR, FIELD, METHOD, TYPE, ANNOTATION_TYPE})
public @interface pkg {}
