# AET Modifier template

> Work in progress...

## How to build

Run `./gradlew build`.

## How to deploy on local dev environment

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