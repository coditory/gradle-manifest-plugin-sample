package com.coditory.sandbox

import com.coditory.sandbox.base.UsesFiles
import spock.lang.Specification

import static com.coditory.sandbox.base.MapAssertions.assertMapEqual

class ManifestReaderTest extends Specification implements UsesFiles {
    ManifestReader reader = new ManifestReader(classLoader)

    def "should read MANIFEST.MF file"() {
        given:
            String appManifest = manifestContent(
                    "Manifest-Version: 1.0",
                    "Implementation-Title: my-app",
                    "Implementation-Version: unspecified",
                    "Built-By: mendlik",
                    "Built-Host: mendlik-dell-e6540",
                    "Built-Date: 2021-02-20T08:31:33Z",
                    "Built-OS: Linux 5.8.0-43-generic amd64",
                    "Built-JDK: 14.0.1 AdoptOpenJDK",
            )
            writeClasspathFile("META-INF/MANIFEST.MF", appManifest)
        when:
            Map<String, String> manifest = reader.readManifestMapWithTitle("my-app")
        then:
            assertMapEqual(manifest, [
                    'Manifest-Version'      : '1.0',
                    'Implementation-Title'  : 'my-app',
                    'Implementation-Version': 'unspecified',
                    'Built-By'              : 'mendlik',
                    'Built-Host'            : 'mendlik-dell-e6540',
                    'Built-Date'            : '2021-02-20T08:31:33Z',
                    'Built-OS'              : 'Linux 5.8.0-43-generic amd64',
                    'Built-JDK'             : '14.0.1 AdoptOpenJDK'
            ])
    }

    def "should return null when there is no manifest.mf file with specified title"() {
        given:
            String libManifest = manifestContent(
                    "Implementation-Title: some-lib",
                    "Built-Host: mendlik-dell-e6540"
            )
            writeClasspathFile("META-INF/MANIFEST.MF", libManifest)
        when:
            Map<String, String> manifest = reader.readManifestMapWithTitle("my-app")
        then:
            manifest == null
    }

    def "should return manifest with specified title when there are multiple MANIFEST.MF on classpath"() {
        given:
            String libManifest = manifestContent(
                    "Implementation-Title: some-lib",
                    "Built-Host: mendlik-dell-e6540"
            )
            String appManifest = manifestContent(
                    "Implementation-Title: my-app",
                    "Built-By: mendlik"
            )
            writeClasspathFiles("META-INF/MANIFEST.MF", [libManifest, appManifest])
        when:
            Map<String, String> manifest = reader.readManifestMapWithTitle("my-app")
        then:
            assertMapEqual(manifest, [
                    'Implementation-Title': 'my-app',
                    'Built-By'            : 'mendlik'
            ])
    }

    private String manifestContent(String... lines) {
        // must end with new line
        return lines.join("\n") + "\n"
    }
}
