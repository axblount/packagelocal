package axblount.packagelocal;

import java.lang.annotation.*;
import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;

/**
 * Packages marked by this annotation will checked to
 * verify that all package local elements have are marked
 * with the annotation {@link PackageLocal}
 * and that all elements marked with {@link PackageLocal}
 * are indeed package local.
 *
 * @see axblount.packagelocal.PackageLocal
 * @see axblount.packagelocal.pkg
 */
@Retention(SOURCE)
@Target(PACKAGE)
public @interface VerifyPackageLocal {}