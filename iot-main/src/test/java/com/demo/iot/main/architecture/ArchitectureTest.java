package com.demo.iot.main.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class ArchitectureTest {

    private static final String BASE_PACKAGE = "com.demo.iot.main";

    private final JavaClasses jc = new ClassFileImporter().withImportOption(new ImportOption.DoNotIncludeTests())
        .importPackages(BASE_PACKAGE);

    @Test
    public void controllerPackageTest() {
        ArchRule r1 = ArchRuleDefinition.classes()
            .that().resideInAPackage("..controller..")
            .should()
            .haveSimpleNameEndingWith("Controller")
            .andShould()
            .onlyDependOnClassesThat().resideInAnyPackage("com.demo.iot.main..", "io.swagger..", "lombok..",
                "org.springframework..", "springfox.documentation.annotations..", "java.lang")
            .andShould().beAnnotatedWith(RestController.class)
            .andShould().beAnnotatedWith(RequestMapping.class);
        r1.check(jc);
    }

    @Test
    public void configPackageTest() {
        ArchRuleDefinition.classes()
            .that().resideInAPackage("..config..")
            .should()
            .beAnnotatedWith(Configuration.class)
            .andShould().haveSimpleNameEndingWith("Config")
            .check(jc);
    }

    @Test
    public void dtoPackageTest() {
        ArchRule r1 = ArchRuleDefinition.classes()
            .that().resideInAPackage("..dto..")
            .should()
            .onlyDependOnClassesThat()
            .resideInAnyPackage("lombok..", "java.lang");
        r1.check(jc);
    }

    @Test
    public void dtoUsageTest() {
        ArchRule r1 = ArchRuleDefinition.classes()
            .that().resideInAPackage("..dto..")
            .should()
            .haveSimpleNameEndingWith("Dto")
            .andShould()
            .onlyBeAccessed()
            .byAnyPackage("com.demo.iot.main.controller..", "com.demo.iot.main.mapper..", "com.demo.iot.main.dto..");
        r1.check(jc);
    }

    @Test
    public void repositoryPackageTest() {
        ArchRuleDefinition.classes()
            .that()
            .resideInAPackage("com.demo.iot.main.repository..")
            .should()
            .haveSimpleNameEndingWith("Repository")
            .andShould()
            .onlyBeAccessed().byAnyPackage("com.demo.iot.main.service..")
            .check(jc);
    }

    @Test
    public void entityPackageTest() {
        ArchRuleDefinition.classes()
            .that()
            .resideInAPackage("com.demo.iot.main.entity..")
            .should()
            .haveSimpleNameEndingWith("Entity")
            .andShould()
            .onlyBeAccessed().byAnyPackage("com.demo.iot.main.entity..", "com.demo.iot.main.mapper..", "com.demo.iot.main.service..")
            .check(jc);
    }
}
