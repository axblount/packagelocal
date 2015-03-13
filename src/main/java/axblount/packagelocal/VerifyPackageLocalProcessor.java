package axblount.packagelocal;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * This processor will retrieve all packages that have been marked with the annotation
 * {@link VerifyPackageLocal}. All elements will be checked for proper usage
 * of the {@link PackageLocal} annotation.
 *
 * @see axblount.packagelocal.PackageLocal
 * @see axblount.packagelocal.pkg
 */
@SupportedAnnotationTypes({"axblount.packagelocal.VerifyPackageLocal"})
public class VerifyPackageLocalProcessor extends AbstractProcessor {
    private PackageLocalVisitor packageLocalVisitor = new PackageLocalVisitor();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement type : annotations) {
            // we should only be processing annotations of this type, but just to be sure...
            if (type.getQualifiedName().contentEquals("axblount.packagelocal.VerifyPackageLocal"))
                for (Element elem : roundEnv.getElementsAnnotatedWith(type))
                    elem.accept(packageLocalVisitor, processingEnv.getMessager());
        }
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
