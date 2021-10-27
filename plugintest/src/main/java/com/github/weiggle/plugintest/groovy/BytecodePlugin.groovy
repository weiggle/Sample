package com.github.weiggle.plugintest.groovy

import org.gradle.api.Plugin
import org.gradle.api.Project


class BytecodePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task('hello') {
            doLast {
                println 'Hello from the Greeting Plugin'
            }
        }
    }
}