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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BackingApplicationTest {

	@Test
	void stringRepresentationShouldNotExposeSensitiveInformationFromTheEnvironment() {
		BackingApplication backingApp = BackingApplication
			.builder()
			.name("Test")
			.environment("privateKey", "secret-private-key")
			.environment("databasePassword", "password")
			.build();

		String backingAppAsString = backingApp.toString();

		assertThat(backingAppAsString).doesNotContain("secret-private-key");
		assertThat(backingAppAsString).doesNotContain("password");

		assertThat(backingAppAsString).contains("privateKey=<value hidden>");
		assertThat(backingAppAsString).contains("databasePassword=<value hidden>");

		assertThat(backingApp.getEnvironment().get("privateKey")).isEqualTo("secret-private-key");
		assertThat(backingApp.getEnvironment().get("databasePassword")).isEqualTo("password");

		backingApp.setEnvironment(null);
		assertThat(backingApp.toString()).isNotEmpty();
	}

}
