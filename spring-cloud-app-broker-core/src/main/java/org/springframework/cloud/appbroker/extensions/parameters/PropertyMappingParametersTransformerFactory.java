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

package org.springframework.cloud.appbroker.extensions.parameters;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import reactor.core.publisher.Mono;

import org.springframework.cloud.appbroker.deployer.BackingApplication;

public class PropertyMappingParametersTransformerFactory extends
	ParametersTransformerFactory<BackingApplication, PropertyMappingParametersTransformerFactory.Config> {

	public PropertyMappingParametersTransformerFactory() {
		super(Config.class);
	}

	@Override
	public ParametersTransformer<BackingApplication> create(Config config) {
		return (backingApplication, parameters) -> transform(backingApplication, parameters, config.getIncludes());
	}

	private Mono<BackingApplication> transform(BackingApplication backingApplication,
		Map<String, Object> parameters,
		List<String> include) {
		if (parameters != null) {
			parameters.keySet().stream()
				.filter(include::contains)
				.forEach(key -> backingApplication.addProperty(key, parameters.get(key).toString()));
		}
		return Mono.just(backingApplication);
	}

	@SuppressWarnings("WeakerAccess")
	public static class Config {

		private String include;

		public List<String> getIncludes() {
			return Arrays.asList(include.split(","));
		}

		public void setInclude(String include) {
			this.include = include;
		}

	}

}
