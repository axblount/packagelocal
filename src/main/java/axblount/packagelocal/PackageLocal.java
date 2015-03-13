package axblount.packagelocal;

import java.lang.annotation.*;
import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;

/**
 * This annotation marks a constructor, field,
 * method, class, interface, enum, or annotation as
 * local to its package.
 *
 * For an abbreviated, "keyword style" version see {@link pkg}.
 *
 * @see axblount.packagelocal.pkg
 */
@Retention(SOURCE)
@Target({CONSTRUCTOR, FIELD, METHOD, TYPE, ANNOTATION_TYPE})
public @interface PackageLocal {}

