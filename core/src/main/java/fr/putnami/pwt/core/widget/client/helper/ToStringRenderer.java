/**
 * This file is part of pwt.
 *
 * pwt is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * pwt is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with pwt.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.putnami.pwt.core.widget.client.helper;

import com.google.gwt.text.shared.AbstractRenderer;

public final class ToStringRenderer extends AbstractRenderer<Object> {

	private static ToStringRenderer INSTANCE;

	public static ToStringRenderer get() {
		if (INSTANCE == null) {
			INSTANCE = new ToStringRenderer();
		}
		return INSTANCE;
	}

	public ToStringRenderer() {
	}

	@Override
	public String render(Object object) {
		if (object == null) {
			return "";
		}

		return object.toString();
	}
}
