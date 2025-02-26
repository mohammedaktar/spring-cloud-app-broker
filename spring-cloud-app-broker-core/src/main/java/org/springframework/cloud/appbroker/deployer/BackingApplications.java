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
import java.util.List;

import org.springframework.util.CollectionUtils;

public class BackingApplications extends ArrayList<BackingApplication> {

	private static final long serialVersionUID = 159473836238657105L;

	private BackingApplications() {
		super();
	}

	public BackingApplications(List<BackingApplication> backingApplications) {
		super();
		super.addAll(backingApplications);
	}

	public static BackingApplicationsBuilder builder() {
		return new BackingApplicationsBuilder();
	}

	public static final class BackingApplicationsBuilder {

		private final List<BackingApplication> backingApplications = new ArrayList<>();

		public BackingApplicationsBuilder backingApplication(BackingApplication backingApplication) {
			if (backingApplication != null) {
				this.backingApplications.add(backingApplication);
			}
			return this;
		}

		public BackingApplicationsBuilder backingApplications(List<BackingApplication> backingApplications) {
			if (!CollectionUtils.isEmpty(backingApplications)) {
				backingApplications.forEach(backingApplication -> this.backingApplication(BackingApplication.builder()
					.backingApplication(backingApplication)
					.build()));
			}
			return this;
		}

		public BackingApplications build() {
			return new BackingApplications(backingApplications);
		}

	}

}
