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

public class ServicesSpec {

	private String serviceInstanceName;

	private ServicesSpec() {
	}

	public ServicesSpec(String serviceInstanceName) {
		this.serviceInstanceName = serviceInstanceName;
	}

	public String getServiceInstanceName() {
		return serviceInstanceName;
	}

	public void setServiceInstanceName(String serviceInstanceName) {
		this.serviceInstanceName = serviceInstanceName;
	}

	public static ServicesSpecBuilder builder() {
		return new ServicesSpecBuilder();
	}

	public static final class ServicesSpecBuilder {

		private String serviceInstanceName;

		private ServicesSpecBuilder() {
		}

		public ServicesSpecBuilder spec(ServicesSpec spec) {
			return this.serviceInstanceName(spec.getServiceInstanceName());
		}

		public ServicesSpecBuilder serviceInstanceName(String serviceInstanceName) {
			this.serviceInstanceName = serviceInstanceName;
			return this;
		}

		public ServicesSpec build() {
			return new ServicesSpec(serviceInstanceName);
		}

	}

}
