// SPDX-License-Identifier: Apache-2.0
package org.gradlex.maven.gmm.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.gradlex.maven.gmm.checksums.HashUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class HashUtilTest {

    @TempDir
    File tmp;

    @Test
    void leading_zeros_are_not_dropped() throws IOException {
        File example = new File(tmp, "example.jar");
        Files.write(example.toPath(), "j(R1wzR*y[^GxWJ5B>L{-HLETRD".getBytes());

        assertThat(HashUtil.md5(example).asHexString()).isEqualTo("00000000000003695b3ae70066f60d42");
    }

    @Test
    void checksums_have_expected_length() throws IOException {
        File example = new File(tmp, "example.jar");
        Files.write(example.toPath(), new byte[] {0, 1, 2, 3, 4});

        assertThat(HashUtil.sha1(example).asHexString().length()).isEqualTo(40);
        assertThat(HashUtil.sha256(example).asHexString().length()).isEqualTo(64);
        assertThat(HashUtil.sha512(example).asHexString().length()).isEqualTo(128);
        assertThat(HashUtil.md5(example).asHexString().length()).isEqualTo(32);
    }
}
