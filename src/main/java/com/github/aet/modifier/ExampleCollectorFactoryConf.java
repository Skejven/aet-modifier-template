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
package com.github.aet.modifier;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "AET Example Modifier Factory", description = "AET Example Modifier Factory Config")
public @interface ExampleCollectorFactoryConf {

  @AttributeDefinition(
      name = "Default background color",
      description = "This colour will be applied to the background color if none is provided in the test configuration.",
      type = AttributeType.STRING)
  String defaultColor() default "red";

}