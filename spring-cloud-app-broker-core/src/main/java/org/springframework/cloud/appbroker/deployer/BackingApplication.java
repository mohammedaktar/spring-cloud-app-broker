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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

@SuppressWarnings("PMD.GodClass")
public class BackingApplication {

	private static final String VALUE_HIDDEN = "<value hidden>";

	private String name;
	private String path;
	private Map<String, String> properties;
	private Map<String, Object> environment;
	private List<ServicesSpec> services;
	private List<ParametersTransformerSpec> parametersTransformers;
	private List<CredentialProviderSpec> credentialProviders;

	private BackingApplication() {
	}

	BackingApplication(String name, String path,
					   Map<String, String> properties,
					   Map<String, Object> environment,
					   List<ServicesSpec> services,
					   List<ParametersTransformerSpec> parametersTransformers,
					   List<CredentialProviderSpec> credentialProviders) {
		this.name = name;
		this.path = path;
		this.properties = properties;
		this.environment = environment;
		this.services = services;
		this.parametersTransformers = parametersTransformers;
		this.credentialProviders = credentialProviders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public void addProperty(String key, String value) {
		this.properties.put(key, value);
	}

	public Map<String, Object> getEnvironment() {
		return environment;
	}

	public void setEnvironment(Map<String, Object> environment) {
		this.environment = environment;
	}

	public void addEnvironment(String key, Object value) {
		environment.put(key, value);
	}

	public List<ServicesSpec> getServices() {
		return services;
	}

	public void setServices(List<ServicesSpec> services) {
		this.services = services;
	}

	public List<ParametersTransformerSpec> getParametersTransformers() {
		return parametersTransformers;
	}

	public void setParametersTransformers(List<ParametersTransformerSpec> parametersTransformers) {
		this.parametersTransformers = parametersTransformers;
	}

	public List<CredentialProviderSpec> getCredentialProviders() {
		return credentialProviders;
	}

	public void setCredentialProviders(List<CredentialProviderSpec> credentialProviders) {
		this.credentialProviders = credentialProviders;
	}

	public static BackingApplicationBuilder builder() {
		return new BackingApplicationBuilder();
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof BackingApplication)) {
			return false;
		}
		BackingApplication that = (BackingApplication) o;
		return Objects.equals(name, that.name) &&
			Objects.equals(path, that.path) &&
			Objects.equals(properties, that.properties) &&
			Objects.equals(environment, that.environment) &&
			Objects.equals(services, that.services) &&
			Objects.equals(parametersTransformers, that.parametersTransformers) &&
			Objects.equals(credentialProviders, that.credentialProviders);
	}

	@Override
	public final int hashCode() {
		return Objects.hash(name, path, properties, environment, services,
			parametersTransformers, credentialProviders);
	}

	@Override
	public String toString() {
		return "BackingApplication{" +
			"name='" + name + '\'' +
			", path='" + path + '\'' +
			", properties=" + properties +
			", environment=" + sanitizeEnvironment(environment) +
			", services=" + services +
			", parametersTransformers=" + parametersTransformers +
			", credentialProviders=" + credentialProviders +
			'}';
	}

	private Map<String, Object> sanitizeEnvironment(Map<String, Object> environment) {
		if (environment == null) {
			return null;
		}

		HashMap<String, Object> sanitizedEnvironment = new HashMap<>();
		environment.forEach((key, value) -> sanitizedEnvironment.put(key, VALUE_HIDDEN));

		return sanitizedEnvironment;
	}

	public static class BackingApplicationBuilder {

		private String name;

		private String path;

		private final Map<String, String> properties = new HashMap<>();

		private final Map<String, Object> environment = new HashMap<>();

		private final List<ServicesSpec> services = new ArrayList<>();

		private final List<ParametersTransformerSpec> parameterTransformers = new ArrayList<>();

		private final List<CredentialProviderSpec> credentialProviders = new ArrayList<>();

		BackingApplicationBuilder() {
		}

		public BackingApplicationBuilder backingApplication(BackingApplication backingApplication) {
			this.name(backingApplication.getName())
				.path(backingApplication.getPath())
				.properties(backingApplication.getProperties())
				.environment(backingApplication.getEnvironment());
				if (!CollectionUtils.isEmpty(backingApplication.getServices())) {
					this.services(backingApplication.getServices().stream()
						.map(spec -> ServicesSpec.builder()
							.spec(spec)
							.build())
						.collect(Collectors.toList()));
				}
				if (!CollectionUtils.isEmpty(backingApplication.getParametersTransformers())) {
					this.parameterTransformers(backingApplication.getParametersTransformers().stream()
						.map(spec -> ParametersTransformerSpec.builder()
							.spec(spec)
							.build())
						.collect(Collectors.toList()));
				}
				if (!CollectionUtils.isEmpty(backingApplication.getCredentialProviders())) {
					this.credentialProviders(backingApplication.getCredentialProviders().stream()
						.map(spec -> CredentialProviderSpec.builder()
							.spec(spec)
							.build())
						.collect(Collectors.toList()));
				}
				return this;
		}

		public BackingApplicationBuilder name(String name) {
			this.name = name;
			return this;
		}

		public BackingApplicationBuilder path(String path) {
			this.path = path;
			return this;
		}

		public BackingApplicationBuilder property(String key, String value) {
			if (key != null && value != null) {
				this.properties.put(key, value);
			}
			return this;
		}

		public BackingApplicationBuilder properties(Map<String, String> properties) {
			if (!CollectionUtils.isEmpty(properties)) {
				this.properties.putAll(properties);
			}
			return this;
		}

		public BackingApplicationBuilder environment(String key, String value) {
			if (key != null && value != null) {
				this.environment.put(key, value);
			}
			return this;
		}

		public BackingApplicationBuilder environment(Map<String, Object> environment) {
			if (!CollectionUtils.isEmpty(environment)) {
				this.environment.putAll(environment);
			}
			return this;
		}

		public BackingApplicationBuilder services(List<ServicesSpec> services) {
			if (!CollectionUtils.isEmpty(services)) {
				this.services.addAll(services);
			}
			return this;
		}

		public BackingApplicationBuilder services(ServicesSpec... services) {
			if (services != null) {
				this.services(Arrays.asList(services));
			}
			return this;
		}

		public BackingApplicationBuilder parameterTransformers(List<ParametersTransformerSpec> parameterTransformers) {
			if (!CollectionUtils.isEmpty(parameterTransformers)) {
				this.parameterTransformers.addAll(parameterTransformers);
			}
			return this;
		}

		public BackingApplicationBuilder parameterTransformers(ParametersTransformerSpec... parameterTransformers) {
			if (parameterTransformers != null) {
				this.parameterTransformers(Arrays.asList(parameterTransformers));
			}
			return this;
		}

		public BackingApplicationBuilder credentialProviders(List<CredentialProviderSpec> credentialProviders) {
			if (!CollectionUtils.isEmpty(credentialProviders)) {
				this.credentialProviders.addAll(credentialProviders);
			}
			return this;
		}

		public BackingApplicationBuilder credentialProviders(CredentialProviderSpec... credentialProviders) {
			if (credentialProviders != null) {
				this.credentialProviders(Arrays.asList(credentialProviders));
			}
			return this;
		}

		public BackingApplication build() {
			return new BackingApplication(name, path, properties, environment, services,
				parameterTransformers, credentialProviders);
		}
	}
}
