package com.antonio.sharedtasks.architecture

import com.lemonappdev.konsist.api.Konsist
import kotlin.test.Test
import kotlin.test.assertTrue

class ArchitectureTest {
    @Test
    fun showFilesFromProject() {
       assertTrue {
           Konsist.scopeFromProject().files.isNotEmpty()
       }
    }
}