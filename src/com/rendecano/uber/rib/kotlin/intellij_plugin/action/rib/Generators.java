/*
 * Copyright (C) 2017. Uber Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package com.rendecano.uber.rib.kotlin.intellij_plugin.action.rib;

import com.google.common.collect.ImmutableList;
import com.rendecano.uber.rib.kotlin.intellij_plugin.generator.GeneratorPair;
import com.rendecano.uber.rib.kotlin.intellij_plugin.generator.rib.BuilderGenerator;
import com.rendecano.uber.rib.kotlin.intellij_plugin.generator.rib.InteractorWithEmptyPresenterGenerator;
import com.rendecano.uber.rib.kotlin.intellij_plugin.generator.rib.InteractorWithEmptyPresenterTestGenerator;
import com.rendecano.uber.rib.kotlin.intellij_plugin.generator.rib.InteractorWithPresenterGenerator;
import com.rendecano.uber.rib.kotlin.intellij_plugin.generator.rib.InteractorWithPresenterTestGenerator;
import com.rendecano.uber.rib.kotlin.intellij_plugin.generator.rib.RouterGenerator;
import com.rendecano.uber.rib.kotlin.intellij_plugin.generator.rib.RouterTestGenerator;
import com.rendecano.uber.rib.kotlin.intellij_plugin.generator.rib.ViewBuilderGenerator;
import com.rendecano.uber.rib.kotlin.intellij_plugin.generator.rib.ViewGenerator;
import com.rendecano.uber.rib.kotlin.intellij_plugin.generator.rib.ViewRouterGenerator;
import com.rendecano.uber.rib.kotlin.intellij_plugin.generator.rib.ViewRouterTestGenerator;

/** Utility methods for getting lists of generators in different configurations. */
public final class Generators {

  private Generators() {}

  /**
   * @param packageName to use for generators.
   * @param ribName to use for generators.
   * @return a list of generators to use when generating a rib with a presenter and view.
   */
  static GeneratorPair getGeneratorsForRibWithPresenterAndView(
      String packageName, String ribName) {
    InteractorWithPresenterGenerator interactorGenerator =
        new InteractorWithPresenterGenerator(packageName, ribName);
    ViewBuilderGenerator viewBuilderGenerator = new ViewBuilderGenerator(packageName, ribName);
    ViewGenerator viewGenerator = new ViewGenerator(packageName, ribName);
    ViewRouterGenerator viewRouterGenerator = new ViewRouterGenerator(packageName, ribName);

    InteractorWithPresenterTestGenerator interactorWithPresenterTestGenerator =
        new InteractorWithPresenterTestGenerator(packageName, ribName);
    ViewRouterTestGenerator viewRouterTestGenerator =
        new ViewRouterTestGenerator(packageName, ribName);

    return new GeneratorPair(
        ImmutableList.of(
            interactorGenerator,
            viewBuilderGenerator,
            viewGenerator,
            viewRouterGenerator),
        ImmutableList.of(interactorWithPresenterTestGenerator, viewRouterTestGenerator));
  }

  /**
   * @param packageName to use for generators.
   * @param ribName to use for generators.
   * @return a list of generators to use when generating a rib without a presenter and view.
   */
  static GeneratorPair getGeneratorsForRibWithoutPresenterAndView(
      String packageName, String ribName) {
    InteractorWithEmptyPresenterGenerator interactorGenerator =
        new InteractorWithEmptyPresenterGenerator(packageName, ribName);
    BuilderGenerator builderGenerator = new BuilderGenerator(packageName, ribName);
    RouterGenerator routerGenerator = new RouterGenerator(packageName, ribName);

    InteractorWithEmptyPresenterTestGenerator interactorWithEmptyPresenterTestGenerator =
        new InteractorWithEmptyPresenterTestGenerator(packageName, ribName);
    RouterTestGenerator routerTestGenerator = new RouterTestGenerator(packageName, ribName);

    return new GeneratorPair(
        ImmutableList.of(interactorGenerator, builderGenerator, routerGenerator),
        ImmutableList.of(interactorWithEmptyPresenterTestGenerator, routerTestGenerator));
  }
}
