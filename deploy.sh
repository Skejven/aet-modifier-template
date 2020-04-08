#!/bin/bash

# Features
cp features/kotlin-features.xml ./try-me/instance/features/kotlin-features.xml

# Bundle
cp build/libs/aet-modifier-template-1.0.0-SNAPSHOT.jar ./try-me/instance/bundles/aet-modifier-template.jar

# Configs
cp conf/com.github.aet.modifier.ExampleModifier.cfg ./try-me/instance/configs/com.github.aet.modifier.ExampleModifier.cfg
cp conf/com.github.aet.modifier.AnotherModifier.cfg ./try-me/instance/configs/com.github.aet.modifier.AnotherModifier.cfg
