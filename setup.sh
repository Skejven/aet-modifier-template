#!/bin/bash
#
# Copyright (C) 2020 Maciej Laskowski
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# create directory with local AET instane and sample tests
mkdir try-me
mkdir try-me/instance

# download latest AET Swarm example
curl -sS `curl -Ls -o /dev/null -w %{url_effective} https://github.com/Skejven/aet-docker/releases/latest/download/example-aet-swarm.zip` > aet-swarm.zip \
&& unzip -q aet-swarm.zip && mv example-aet-swarm/* try-me/instance \
&& rm -d example-aet-swarm && rm aet-swarm.zip

# download AET bash client and sample suite
mkdir try-me/tests
curl -sS https://raw.githubusercontent.com/Cognifide/aet/master/client/client-scripts/aet.sh > try-me/tests/aet.sh \
&& chmod u+x try-me/tests/aet.sh

cp misc/suite.xml try-me/tests/suite.xml