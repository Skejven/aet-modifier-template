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

import com.cognifide.aet.communication.api.metadata.CollectorStepResult;
import com.cognifide.aet.job.api.collector.CollectorJob;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleModifier implements CollectorJob {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExampleModifier.class);

  private static final String COLOR_PARAM = "color";
  private static final String EXAMPLE_CHANGE_BACKGROUND_MODIFICATION = "document.body.style.background = '%s';";

  private final WebDriver webDriver;
  private final String defaultColor;
  private String color;

  ExampleModifier(WebDriver webDriver, String defaultColor) {
    this.webDriver = webDriver;
    this.defaultColor = defaultColor;
  }

  @Override
  public CollectorStepResult collect() {
    CollectorStepResult result;
    try {
      LOGGER.trace("Example modifier will apply {} bg colour", color);
      final String command = String.format(EXAMPLE_CHANGE_BACKGROUND_MODIFICATION, color);
      ((JavascriptExecutor) webDriver).executeScript(command);
      result = CollectorStepResult.newModifierResult();
    } catch (Exception e) {
      final String message = String
          .format("Can't execute JavaScript command. Error: %s ", e.getMessage());
      result = CollectorStepResult.newProcessingErrorResult(message);
      LOGGER.warn(message, e);
    }
    return result;
  }

  @Override
  public void setParameters(Map<String, String> params) {
    color = params.getOrDefault(COLOR_PARAM, defaultColor);
  }
}
