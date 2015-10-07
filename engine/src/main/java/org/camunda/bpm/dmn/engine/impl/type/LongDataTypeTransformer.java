/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.camunda.bpm.dmn.engine.impl.type;

import org.camunda.bpm.dmn.engine.type.DataTypeTransformer;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.LongValue;
import org.camunda.bpm.engine.variable.value.TypedValue;

/**
 * Transform values of type {@link Number} and {@link String} into {@link LongValue}.
 *
 * @author Philipp Ossler
 *
 */
public class LongDataTypeTransformer implements DataTypeTransformer {

  @Override
  public TypedValue transform(Object value) throws IllegalArgumentException {
    if (value instanceof Number) {
      long longValue = transformNumber((Number) value);
      return Variables.longValue(longValue);

    } else if (value instanceof String) {
      long longValue = transformString((String) value);
      return Variables.longValue(longValue);

    } else {
      throw new IllegalArgumentException();
    }
  }

  protected long transformNumber(Number value) {
    if(isLong(value)) {
      return value.longValue();
    } else {
      throw new IllegalArgumentException();
    }
  }

  protected boolean isLong(Number value) {
    double doubleValue = value.doubleValue();
    return doubleValue == (long) doubleValue;
  }

  protected long transformString(String value) {
    return Long.parseLong(value);
  }

}