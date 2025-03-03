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

package org.gradlex.maven.gmm.test;

import org.gradlex.maven.gmm.checksums.HashUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

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
