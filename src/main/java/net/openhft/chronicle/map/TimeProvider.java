/*
 * Copyright 2014 Higher Frequency Trading http://www.higherfrequencytrading.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.openhft.chronicle.map;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * TimeProvider was created initially to support testing of ReplicatedChronicleMap,
 * with the aim of possibly later providing and optimization to System.currentTimeMillis()
 * on every call to put(), get() and remove()
 * <p/>
 * Subclasses should be immutable, because {@link ChronicleMapBuilder} doesn't make defensive copies.
 *
 * @author Rob Austin.
 */
public abstract class TimeProvider implements Serializable {
    public static final TimeProvider SYSTEM = new System();
    private static final long serialVersionUID = 0L;

    public abstract long currentTimeMillis();

    private static class System extends TimeProvider {
        private static final long serialVersionUID = 0L;

        public long currentTimeMillis() {
            return java.lang.System.currentTimeMillis();
        }

        @Override
        public boolean equals(Object o) {
            return o != null && o.getClass() == getClass();
        }

        @Override
        public int hashCode() {
            return getClass().hashCode();
        }

        @Override
        public String toString() {
            return getClass().getSimpleName();
        }

        private Object readResolve() throws ObjectStreamException {
            return SYSTEM;
        }
    }

}
