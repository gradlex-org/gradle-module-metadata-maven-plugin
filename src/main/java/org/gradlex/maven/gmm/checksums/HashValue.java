// SPDX-License-Identifier: Apache-2.0
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
