/*
 * Copyright the GradleX team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradlex.maven.gmm.checksums;

import java.util.Arrays;

public class HashValue {

    private final byte[] digest;

    public HashValue(byte[] digest) {
        this.digest = digest;
    }

    public String asHexString() {
        StringBuilder sb = new StringBuilder(digest.length * 2);
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HashValue)) {
            return false;
        }

        HashValue otherHashValue = (HashValue) other;
        return Arrays.equals(digest, otherHashValue.digest);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(digest);
    }
}
