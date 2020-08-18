package no.uio.jus.pluricourts;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("no.uio.jus.pluricourts");

        noClasses()
            .that()
            .resideInAnyPackage("no.uio.jus.pluricourts.service..")
            .or()
            .resideInAnyPackage("no.uio.jus.pluricourts.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..no.uio.jus.pluricourts.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
