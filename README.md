Package Local
=============

This library adds a package local annotation to Java. Adding the jar to your classpath will cause two things to happen in packages annotated with `@VerifyPackageLocal`:

* All package local items must be annotated with `@PackageLocal` or a compile error will be raised.
* All items that are not package local must not be annotated with `@PackageLocal` or a compile error will be raised.

For inline usage, the annotation `@pkg` is also available. The usage is exactly the same as `@PackageLocal`.

Example
-------

`package-info.java`

```java
@VerifyPackageLocal
package my.package;

import axblount.packagelocal.VerifyPackageLocal;
```

`Test.java`

```java
package my.package;

@PackageLocal
class Test {
    @pkg int x;

    @PackageLocal
    Test() { ... }

    @pkg void foo() { ... }
}
```

License
=======

Public domain.
