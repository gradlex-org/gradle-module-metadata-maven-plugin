// SPDX-License-Identifier: Apache-2.0
package org.gradlex.maven.gmm.checksums;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    private static HashValue createHash(File file, String algorithm) {
        try {
            return createHash(new FileInputStream(file), algorithm);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static HashValue createHash(InputStream instr, String algorithm) {
        MessageDigest messageDigest;
        try {
            messageDigest = createMessageDigest(algorithm);
            byte[] buffer = new byte[4096];
            try {
                while (true) {
                    int nread = instr.read(buffer);
                    if (nread < 0) {
                        break;
                    }
                    messageDigest.update(buffer, 0, nread);
                }
            } finally {
                instr.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new HashValue(messageDigest.digest());
    }

    private static MessageDigest createMessageDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashValue sha1(File file) {
        return createHash(file, "SHA1");
    }

    public static HashValue sha256(File file) {
        return createHash(file, "SHA-256");
    }

    public static HashValue sha512(File file) {
        return createHash(file, "SHA-512");
    }

    public static HashValue md5(File file) {
        return createHash(file, "MD5");
    }
}
