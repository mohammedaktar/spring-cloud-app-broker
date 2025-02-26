/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.appbroker.extensions.targets;

import java.util.Map;

import org.springframework.cloud.appbroker.deployer.DeploymentProperties;

public class SpacePerServiceInstance extends TargetFactory<SpacePerServiceInstance.Config> {

	public SpacePerServiceInstance() {
		super(Config.class);
	}

	@Override
	public Target create(Config config) {
		return this::apply;
	}

	private ArtifactDetails apply(Map<String, String> properties, String name, String serviceInstanceId) {
		properties.put(DeploymentProperties.HOST_PROPERTY_KEY, name + "-" + serviceInstanceId);
		properties.put(DeploymentProperties.TARGET_PROPERTY_KEY, serviceInstanceId);

		return ArtifactDetails.builder()
			.name(name)
			.properties(properties)
			.build();
	}

	public static class Config {
	}

}
