package com.antonio.sharedtasks.architecture

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.api.verify.assertFalse
import kotlin.test.Test
import kotlin.test.assertTrue

class ArchitectureTest {
    @Test
    fun showFilesFromProject() {
       assertTrue {
           Konsist.scopeFromProject().files.isNotEmpty()
       }
    }

    @Test
    fun cleanArchitectureLayersHaveCorrectDependencies() {
        Konsist.scopeFromProduction().assertArchitecture {
            val domain = Layer("Domain", "com.antonio.sharedtasks.domain..")
            val presentation = Layer("Presentation", "com.antonio.sharedtasks.presentation..")
            val data = Layer("Data", "com.antonio.sharedtasks.data..")

            domain.dependsOnNothing()
            presentation.dependsOn(domain)
            data.dependsOn(domain)
        }
    }

    @Test
    fun presentationNotData() {
        Konsist.scopeFromProduction().files.withPackage("com.antonio.sharedtasks.presentation..").assertFalse {
            it.hasImport{ koImport -> koImport.name.startsWith("com.antonio.sharedtasks.data.")}
        }
    }

    @Test
    fun dataNotPresentation() {
        Konsist.scopeFromProduction().files.withPackage("com.antonio.sharedtasks.data..").assertFalse {
            it.hasImport { koImport -> koImport.name.startsWith("com.antonio.sharedtasks.presentation.") }
        }
    }

    @Test
    fun domainNotDependencies() {
        Konsist.scopeFromProduction().files.withPackage("com.antonio.sharedtasks.domain..").assertFalse {
            it.hasImport { koImport -> koImport.name.startsWith("android.") || koImport.name.startsWith("androidx.") ||
                    koImport.name.startsWith("org.jetbrains.compose.") || koImport.name.startsWith("platform.") }
        }
    }
}