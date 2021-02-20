import com.coditory.gradle.manifest.ManifestPluginExtension
import com.coditory.gradle.manifest.ManifestPlugin

plugins {
    java
    groovy
    id("com.coditory.manifest") version "0.1.12"
}

class ManifestModPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.apply(ManifestPlugin.PLUGIN_ID)
        project.plugins.withType(ManifestPlugin::class) {
            project.extensions.configure(ManifestPluginExtension::class) {
                classpathPrefix = "libs"
                buildAttributes = false
            }
        }
    }
}
apply<ManifestModPlugin>()

repositories {
    mavenCentral()
}

the<ManifestPluginExtension>().buildAttributes = true

dependencies {
    implementation("com.coditory.quark:quark-context:0.1.2")
    testImplementation("org.spockframework:spock-core:2.0-M4-groovy-3.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "failed", "skipped")
    }
}