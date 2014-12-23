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

package org.gradle.nativeplatform.toolchain.internal.gcc;

import org.gradle.internal.Transformers;
import org.gradle.nativeplatform.toolchain.internal.*;
import org.gradle.nativeplatform.toolchain.internal.compilespec.ObjectiveCppCompileSpec;

import java.io.File;

public class ObjectiveCppCompiler extends NativeCompiler<ObjectiveCppCompileSpec> {

    public ObjectiveCppCompiler(CommandLineTool commandLineTool, CommandLineToolInvocation baseInvocation, String objectFileSuffix, boolean useCommandFile) {
        super(commandLineTool, baseInvocation, new ObjectiveCppCompileArgsTransformer(), Transformers.<ObjectiveCppCompileSpec>noOpTransformer(), objectFileSuffix, useCommandFile);
    }

    private static class ObjectiveCppCompileArgsTransformer extends GccCompilerArgsTransformer<ObjectiveCppCompileSpec> {
        protected String getLanguage() {
            return "objective-c++";
        }
    }

    protected OptionsFileArgsWriter optionsFileTransformer(ObjectiveCppCompileSpec spec) {
        return new GccOptionsFileArgWriter(spec.getTempDir());
    }

    protected OutputFileArgTransformer outputFileTransformer(File sourceFile, File objectFileDir, String objectFileNameSuffix, boolean windowsPathLengthLimitation) {
        return new GccOutputFileArgTransformer(sourceFile, objectFileDir, objectFileNameSuffix, windowsPathLengthLimitation);
    }
}
