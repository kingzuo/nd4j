/*-
 *
 *  * Copyright 2015 Skymind,Inc.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 *
 */

package org.nd4j.linalg.api.ops.impl.transforms;

import lombok.NoArgsConstructor;
import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.imports.NoOpNameFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Inverse of index permutation.
 *
 * @author Max Pumperla
 */
@NoArgsConstructor
public class InvertPermutation extends BaseDynamicTransformOp {


    public InvertPermutation(SameDiff sameDiff, SDVariable input, boolean inPlace) {
        super( sameDiff, new SDVariable[] {input}, inPlace);
    }

    @Override
    public String opName() {
        return "invert_permutation";
    }

    @Override
    public String onnxName() {
        throw new NoOpNameFoundException("No onnx name found for shape " + opName());
    }

    @Override
    public String tensorflowName() {
        return "InvertPermutation";
    }


    @Override
    public List<SDVariable> doDiff(List<SDVariable> grad) {
        SDVariable gradient = grad.get(0);
        SDVariable invertedGradient = f().invertPermutation(gradient, false);
        return Arrays.asList(invertedGradient);
    }

}
