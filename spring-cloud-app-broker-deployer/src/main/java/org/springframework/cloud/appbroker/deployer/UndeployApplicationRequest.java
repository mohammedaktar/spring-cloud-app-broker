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

package org.springframework.cloud.appbroker.deployer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.CollectionUtils;

public class UndeployApplicationRequest {

	private final String name;

	private final Map<String, String> properties;

	protected UndeployApplicationRequest(String name, Map<String, String> properties) {
		this.name = name;
		this.properties = properties;
	}

	public static UndeployApplicationRequestBuilder builder() {
		return new UndeployApplicationRequestBuilder();
	}

	public String getName() {
		return name;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public static final class UndeployApplicationRequestBuilder {

		private String name;

		private final Map<String, String> properties = new HashMap<>();

		private UndeployApplicationRequestBuilder() {
		}

		public UndeployApplicationRequestBuilder name(String name) {
			this.name = name;
			return this;
		}

		public UndeployApplicationRequestBuilder properties(Map<String, String> properties) {
			if (!CollectionUtils.isEmpty(properties)) {
				this.properties.putAll(properties);
			}
			return this;
		}

		public UndeployApplicationRequest build() {
			return new UndeployApplicationRequest(name, properties);
		}

	}

}
