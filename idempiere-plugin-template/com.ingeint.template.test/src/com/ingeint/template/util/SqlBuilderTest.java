/**
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Copyright (C) 2020 INGEINT <https://www.ingeint.com> and contributors (see README.md file).
 */

package com.ingeint.template.util;

import static com.ingeint.template.test.util.RandomTestUtil.getRandomString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class SqlBuilderTest {

	@Test
	public void thorwsAFileNotFoundExceptionIfFileDoesNotExists() {
		SqlBuilder sqlBuilder = SqlBuilder.builder();

		Throwable actualException = catchThrowable(() -> {
			sqlBuilder.template(getRandomString());
		});

		assertThat(actualException).isInstanceOf(FileNotFoundException.class);
	}

}