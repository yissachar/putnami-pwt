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
package fr.putnami.pwt.core.service.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.impl.Serializer;

public class CommandParam {
	private final boolean lazy;
	private final boolean quiet;
	private final Serializer serializer;
	private final List params;
	private final List callbacks;

	public CommandParam(boolean lazy, boolean quiet, Serializer serializer, List params, List callbacks) {
		super();
		this.lazy = lazy;
		this.quiet = quiet;
		this.serializer = serializer;
		this.params = params;
		this.callbacks = callbacks;
	}

	public boolean isLazy() {
		return lazy;
	}

	public boolean isQuiet() {
		return quiet;
	}

	public Serializer getSerializer() {
		return serializer;
	}

	public List getParams() {
		return params;
	}

	public List<AsyncCallback> getCallbacks() {
		return callbacks;
	}

}
