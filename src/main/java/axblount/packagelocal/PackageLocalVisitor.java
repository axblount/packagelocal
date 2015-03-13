package axblount.packagelocal;

import javax.annotation.processing.Messager;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementKindVisitor8;
import static javax.tools.Diagnostic.Kind.ERROR;
import java.util.Set;

import static javax.lang.model.element.Modifier.*;
import static javax.tools.Diagnostic.Kind.WARNING;

/**
 * This is used to visit each element of a package. If an element has not
 * be properly marked with {@link PackageLocal} and compilation error will be raised.
 */
class PackageLocalVisitor extends ElementKindVisitor8<Void, Messager> {
    /**
     * Verify that an element is annotated properly. This should only be called
     * on elements that can be declared as package local.
     *
     * @param e The element to check.
     * @param messager The messager for error messages.
     */
    private void checkElement(Element e, Messager messager) {
        boolean annotated = e.getAnnotation(PackageLocal.class) != null
                         || e.getAnnotation(pkg.class) != null;
        Set<Modifier> mods = e.getModifiers();
        if (annotated) {
            if (mods.contains(PRIVATE)) {
                messager.printMessage(
                        ERROR,
                        e.asType() + " is annotated as package local but it is private.",
                        e);
            } else if (mods.contains(PROTECTED)) {
                messager.printMessage(
                        ERROR,
                        e.asType() + " is annotated as package local but it is protected.",
                        e);
            } else if (mods.contains(PUBLIC)) {
                messager.printMessage(
                        ERROR,
                        e.asType() + " is annotated as package local but it is public.",
                        e);
            }
        } else if (!(mods.contains(PRIVATE) || mods.contains(PROTECTED) || mods.contains(PUBLIC))) {
            messager.printMessage(
                    ERROR,
                    e.asType() + " is package local but not annotated as such.",
                    e);
        }
    }

    @Override
    public Void visitPackage(PackageElement e, Messager messager) {
        if (e.getAnnotation(VerifyPackageLocal.class) != null) {
            // visit all elements declared in this package.
            for (Element inner : e.getEnclosedElements())
                visit(inner, messager);
        }
        return null;
    }

    @Override
    public Void visitType(TypeElement e, Messager messager) {
        checkElement(e, messager);
        // Visit all enclosed elements of this type.
        for (Element inner : e.getEnclosedElements())
            visit(inner, messager);
        return null;
    }

    @Override
    public Void visitVariableAsField(VariableElement e, Messager messager) {
        // Fields do enclose any types we are interested in checking.
        checkElement(e, messager);
        return null;
    }

    @Override
    public Void visitExecutableAsConstructor(ExecutableElement e, Messager messager) {
        // Constructors do enclose any types we are interested in checking.
        checkElement(e, messager);
        return null;
    }

    @Override
    public Void visitExecutableAsMethod(ExecutableElement e, Messager messager) {
        // Methods do no enclose any types we are interested in checking.
        checkElement(e, messager);
        return null;
    }

    @Override
    public Void visitUnknown(Element e, Messager messager) {
        messager.printMessage(WARNING, "Element type unknown.", e);
        return null;
    }
}
