# AET Modifier template

This repository contains implementation of a sample [AET Modifier](https://github.com/Cognifide/aet/wiki/Modifiers):
- [Modifier written in Java](https://github.com/Skejven/aet-modifier-template/tree/master/src/main/java/com/github/aet/modifier)
- [Modifier written in Kotlin](https://github.com/Skejven/aet-modifier-template/tree/master/src/main/kotlin/com/github/aet/modifier)

AET uses Modifiers to achieve certain conditions before collecting the data (e.g. set browser viewport size or wait for some element to appear). 
In fact, Modifiers are Collectors which use webdriver to perform some actions, bug simply do not collect any data.

## Creating new Modifier

All AET Modifiers implement [`CollectorJob`](https://github.com/Cognifide/aet/blob/master/api/jobs-api/src/main/java/com/cognifide/aet/job/api/collector/CollectorJob.java)
interface. To create a Modifier in AET instance, you will need [`CollectorFactory`](https://github.com/Cognifide/aet/blob/master/api/jobs-api/src/main/java/com/cognifide/aet/job/api/collector/CollectorFactory.java) that provides a new instance of Modifier each time it is required. It also equips Modifier
with WebDriver - so that it can interact with the browser.

You can see the example code in this repository:
- [Modifier written in Java](https://github.com/Skejven/aet-modifier-template/tree/master/src/main/java/com/github/aet/modifier)
- [Modifier written in Kotlin](https://github.com/Skejven/aet-modifier-template/tree/master/src/main/kotlin/com/github/aet/modifier)

More (Java) code examples are in the [AET core Modifiers](https://github.com/Cognifide/aet/tree/master/core/jobs/src/main/java/com/cognifide/aet/job/common/modifiers).

## Repo structure

```
.
├── conf
│   └── com.github.aet.modifier.ExampleModifier.cfg
├── features
│   └── kotlin-features.xml
├── misc
│   └── suite.xml
└── src
    └── main
        ├── java
        │   └── com
        └──  kotlin
            └── com
```

- `conf` contains OSGi configuration files for the modifiers
- `features` contains [Karaf provisioning](https://karaf.apache.org/manual/latest/provisioning) configuration file for `kotlin-osgi-bundle`
- `misc` contains sample suite that can be use to run the test with sample modifiers
- `src` contains code of two sample modifiers: `example` (written in Java) and `another` (written in Kotlin), 
 together with their factories and configs

## How to develop a custom modifier

Choose one of the options:
- [download ZIP](https://github.com/Skejven/aet-modifier-template/archive/master.zip)
- [use this repo as a template](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-repository-from-a-template)

Choose whether you want to develop a Java or Kotlin version.
Hack the modifier's code, rename it do whatever you need using webdriver.
Remember to give your modifier a [unique name](https://github.com/Cognifide/aet/blob/master/api/jobs-api/src/main/java/com/cognifide/aet/job/api/collector/CollectorFactory.java#L33).
With that name it will be triggered when AET test collection phase is processed, e.g.
- When you name your component `my-custom-mod` you will call it with `<my-custom-mod>` from the suite (see the `<example>` in this repo).

Build and deploy it following instructions below.

### How to build

Run `./gradlew build`.

This will produce `jar` in the `build/libs` as well as compress the config file to `build/distributions`.

### How to deploy on local dev environment

Run `./setup.sh`.
This script will create `try-me` directory with:
- latest [AET Docker Example Swarm](https://github.com/Skejven/aet-docker/releases)
- sample suite with downloaded [AET bash client](https://github.com/Cognifide/aet/tree/master/client/client-scripts) and sample suite

You will have following structure:
```
├── instance
│   ├── aet-swarm.yml
│   ├── bundles
│   ├── configs
│   ├── features
│   └── report
└── tests
    ├── aet.sh
    └── suite.xml
```

To deploy example modifier run `./deploy.sh` (that will copy the bundle and configs to proper `instance` directories).

Run the AET instance from the `try-me/instance` directory using `docker stack deploy -c aet-swarm.yml aet`.

When the instance is up (see details in the [AET Doker instance setup guide](https://github.com/Skejven/aet-docker#instance-setup)) you may run sample suite.
From the `tests` directory run `./aet.sh http://localhost:8181`.

Every time you change the code/config of the custom module, simply run `./deploy.sh` and wait a moment for the instance to update (changes are auto-discovered by Karaf).