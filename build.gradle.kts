/*
 * aet-modifier-template
 *
 * Copyright (C) 2020 Maciej Laskowski
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

plugins {
    `java-library`
    `kotlin-dsl`
    id("biz.aQute.bnd.builder")
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(group = "org.osgi", name = "org.osgi.service.component.annotations", version = "1.3.0")
    implementation(group = "org.osgi", name = "org.osgi.service.metatype.annotations", version = "1.3.0")
    implementation(group = "org.osgi", name = "org.osgi.annotation", version = "6.0.0")

    implementation(group = "com.google.code.gson", name = "gson", version = "2.8.5")
    implementation(group = "com.google.guava", name = "guava", version = "25.1-jre")
    implementation(group = "org.apache.commons", name = "commons-lang3", version = "3.7")
    implementation(group = "org.apache.httpcomponents", name = "httpclient", version = "4.4")
    implementation(group = "org.slf4j", name = "slf4j-api", version = "1.7.7")

    api("com.cognifide.aet:communication-api:3.2.2")
    api("com.cognifide.aet:jobs-api:3.2.2")
    api("com.cognifide.aet:selenium:3.2.2")

    testImplementation("junit:junit:4.12")
}

tasks.withType<Jar>().configureEach {
    manifest {
        attributes(Pair("Bundle-Vendor", "Skejven corp."))
        attributes(Pair("Bundle-Description", "AET Extension: example-modifier"))
        attributes(Pair("Bundle-DocURL", "https://github.com/Skejven/aet-modifier-template"))
    }
}

val assembleFeatures = tasks.register<Zip>("assembleFeatures") {
    archiveName = "${project.name}-features.zip"
    from("features")
}

val assembleConfigs = tasks.register<Zip>("assembleConfigs") {
    archiveName = "${project.name}-config.zip"
    from("conf")
}

tasks.named("build") { finalizedBy(assembleFeatures, assembleConfigs) }