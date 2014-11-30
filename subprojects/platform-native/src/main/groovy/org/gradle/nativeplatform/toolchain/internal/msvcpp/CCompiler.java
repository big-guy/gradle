/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.nativeplatform.toolchain.internal.msvcpp;

import org.gradle.api.Transformer;
import org.gradle.nativeplatform.toolchain.internal.NativeCompiler;
import org.gradle.nativeplatform.toolchain.internal.OptionsFileArgsWriter;
import org.gradle.nativeplatform.toolchain.internal.compilespec.CCompileSpec;
import org.gradle.nativeplatform.toolchain.internal.CommandLineTool;
import org.gradle.nativeplatform.toolchain.internal.CommandLineToolInvocation;

class CCompiler extends NativeCompiler<CCompileSpec> {

    CCompiler(CommandLineTool commandLineTool, CommandLineToolInvocation invocation, Transformer<CCompileSpec, CCompileSpec> specTransformer, String objectFileSuffix) {
        super(commandLineTool, invocation, new CCompilerArgsTransformer(), specTransformer, new VisualCppOutputFileArgTransformer(), objectFileSuffix, true);
    }

    protected OptionsFileArgsWriter getOptionsWriter(CCompileSpec spec) {
        return new VisualCppOptionsFileArgWriter(spec.getTempDir());
    }

    private static class CCompilerArgsTransformer extends VisualCppCompilerArgsTransformer<CCompileSpec> {
        protected String getLanguageOption() {
            return "/TC";
        }
    }
}
