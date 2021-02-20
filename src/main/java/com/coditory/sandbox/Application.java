package com.coditory.sandbox;

import java.util.jar.Manifest;
import java.util.stream.Collectors;

class Application {
    public static void main(String[] args) {
        Manifest manifest = ManifestReader.loadManifestWithTitle("gradle-manifest-plugin-sample-single");
        if (manifest == null) {
            System.out.println("Manifest not found");
        } else {
            System.out.println("MANIFEST\n" + toString(manifest));
        }
    }

    private static String toString(Manifest manifest) {
        return manifest.getMainAttributes().entrySet()
                .stream()
                .map(it -> it.getKey() + ": " + it.getValue())
                .collect(Collectors.joining("\n"));
    }
}
