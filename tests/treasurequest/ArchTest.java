package treasurequest;

import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

/**
 * Valide les dépendances entre les paquetages.
 * */
public class ArchTest {
	@Test
	void domainsShouldBeAccessedBySupervisorsAndDomains() {
		JavaClasses classes = new ClassFileImporter().importPackages("treasurequest");
		
		ArchRule myRule = classes()
			    .that().resideInAPackage("treasurequest.domains..")
			    .should().onlyBeAccessed()
			    .byAnyPackage("treasurequest", "treasurequest.supervisors..", "treasurequest.domains..");
		
		myRule.check(classes);
	}
	
	@Test
	void supervisorsShouldOnlyBeAccessedByViewsAndSupervisors() {
		JavaClasses classes = new ClassFileImporter().importPackages("treasurequest");
		
		ArchRule myRule = classes()
			    .that().resideInAPackage("treasurequest.supervisors..")
			    .should().onlyBeAccessed()
			    .byAnyPackage("treasurequest","treasurequest.swing..", "treasurequest.supervisors..");
		
		myRule.check(classes);
	}
	
	@Test
	void viewsShouldOnlyBeAccessedByViews() {
		JavaClasses classes = new ClassFileImporter().importPackages("treasurequest");
		
		ArchRule myRule = classes()
			    .that().resideInAPackage("treasurequest.swing..")
			    .should().onlyBeAccessed()
			    .byAnyPackage("treasurequest","treasurequest.swing..");
		
		myRule.check(classes);
	}
}
