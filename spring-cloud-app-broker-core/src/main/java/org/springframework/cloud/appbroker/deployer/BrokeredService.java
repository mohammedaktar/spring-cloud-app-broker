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

import java.util.Objects;

import org.springframework.util.CollectionUtils;

public class BrokeredService {

	private String serviceName;

	private String planName;

	private BackingApplications apps;

	private BackingServices services;

	private TargetSpec target;

	private BrokeredService() {
		super();
	}

	public BrokeredService(String serviceName, String planName, BackingApplications apps, BackingServices services,
		TargetSpec target) {
		super();
		this.serviceName = serviceName;
		this.planName = planName;
		this.apps = apps;
		this.services = services;
		this.target = target;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public BackingApplications getApps() {
		return apps;
	}

	public void setApps(BackingApplications apps) {
		this.apps = apps;
	}

	public BackingServices getServices() {
		return services;
	}

	public void setServices(BackingServices services) {
		this.services = services;
	}

	public TargetSpec getTarget() {
		return target;
	}

	public void setTarget(TargetSpec target) {
		this.target = target;
	}

	public static BrokeredServiceBuilder builder() {
		return new BrokeredServiceBuilder();
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof BrokeredService)) {
			return false;
		}
		BrokeredService that = (BrokeredService) o;
		return Objects.equals(serviceName, that.serviceName) &&
			Objects.equals(planName, that.planName) &&
			Objects.equals(apps, that.apps) &&
			Objects.equals(services, that.services) &&
			Objects.equals(target, that.target);
	}

	@Override
	public final int hashCode() {
		return Objects.hash(serviceName, planName, apps, services, target);
	}

	@Override
	public String toString() {
		return "BrokeredService{" +
			"serviceName='" + serviceName + '\'' +
			", planName='" + planName + '\'' +
			", apps=" + apps +
			", services=" + services +
			", target=" + target +
			'}';
	}

	public static class BrokeredServiceBuilder {

		private String id;

		private String planId;

		private BackingApplications backingApplications;

		private BackingServices backingServices;

		private TargetSpec target;

		public BrokeredServiceBuilder serviceName(String id) {
			this.id = id;
			return this;
		}

		public BrokeredServiceBuilder planName(String planId) {
			this.planId = planId;
			return this;
		}

		public BrokeredServiceBuilder apps(BackingApplications backingApplications) {
			if (!CollectionUtils.isEmpty(backingApplications)) {
				this.backingApplications = BackingApplications.builder()
					.backingApplications(backingApplications)
					.build();
			}
			return this;
		}

		public BrokeredServiceBuilder services(BackingServices backingServices) {
			if (!CollectionUtils.isEmpty(backingServices)) {
				this.backingServices = BackingServices.builder()
					.backingServices(backingServices)
					.build();
			}
			return this;
		}

		public BrokeredServiceBuilder target(TargetSpec target) {
			this.target = target;
			return this;
		}

		public BrokeredService build() {
			return new BrokeredService(id, planId, backingApplications, backingServices, target);
		}

	}

}
