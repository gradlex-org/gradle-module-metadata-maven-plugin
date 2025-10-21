// SPDX-License-Identifier: Apache-2.0
package org.gradlex.maven.gmm;

/**
 * <a href="https://docs.gradle.org/current/userguide/component_capabilities.html">
 *     docs.gradle.org/current/userguide/component_capabilities.html
 * </a>
 */
public class Capability {
    private String groupId;
    private String artifactId;
    private String version;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
