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
package fr.putnami.pwt.core.widget.client;

import java.util.Collections;

import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;

import fr.putnami.pwt.core.editor.client.EditorInput;
import fr.putnami.pwt.core.editor.client.EditorLeaf;
import fr.putnami.pwt.core.editor.client.EditorOutput;
import fr.putnami.pwt.core.editor.client.Error;
import fr.putnami.pwt.core.editor.client.Visitor;
import fr.putnami.pwt.core.editor.client.helper.MessageHelper;
import fr.putnami.pwt.core.editor.client.validator.Validator;
import fr.putnami.pwt.core.model.client.ModelDriver;
import fr.putnami.pwt.core.model.client.base.EditorModel;
import fr.putnami.pwt.core.model.client.base.HasReadonly;
import fr.putnami.pwt.core.model.client.model.Model;
import fr.putnami.pwt.core.model.client.visitor.ReadonlyVisitor;
import fr.putnami.pwt.core.widget.client.base.AbstractPanel;
import fr.putnami.pwt.core.widget.client.base.AbstractTableCell;

public class TableRow<T> extends AbstractPanel implements
		EditorOutput<T>,
		EditorInput<T>,
		EditorLeaf,
		EditorModel<T>,
		HasReadonly,
		HasClickHandlers {

	private MessageHelper messageHelper;
	private Model<T> model;
	private ModelDriver<T> driver;

	private int index;

	private Boolean readonly;

	public TableRow() {
		super(TableRowElement.TAG);
	}

	protected TableRow(TableRow<T> source) {
		super(source);
		cloneSourceWidgets(source);
	}

	@Override
	public IsWidget cloneWidget() {
		return new TableRow<T>(this);
	}

	@Override
	public void add(IsWidget child) {
		if (child instanceof AbstractTableCell || child instanceof TableTH) {
			append(child);
		}
	}

	@Override
	public Model<T> getModel() {
		return this.model;
	}

	@Override
	public void setMessageHelper(MessageHelper messageHelper) {
		this.messageHelper = messageHelper;
	}

	@Override
	public void initialize(Model<T> model, Visitor... visitors) {
		assert this.model == null : "model can not be set twice.";
		this.model = model;
		this.driver = new ModelDriver<T>(model);
		this.driver.setMessageHelper(messageHelper);
		this.driver.initialize(this, visitors);
		this.driver.accept(new ReadonlyVisitor(this, readonly, true));
	}

	@Override
	public Boolean getReadonly() {
		return readonly;
	}

	@Override
	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
		if (this.driver != null) {
			this.driver.accept(new ReadonlyVisitor(this, readonly, true));
		}
	}

	@Override
	public T flush() {
		return this.driver.flush();
	}

	@Override
	public void edit(T value) {
		this.driver.edit(value);
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public T getValue() {
		return driver.getValue();
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

	@Override
	public boolean hasErrors() {
		return driver == null ? false : driver.hasErrors();
	}

	@Override
	public Iterable<Error> getErrors() {
		return driver == null ? Collections.EMPTY_LIST : driver.getErrors();
	}

	@Override
	public void addValidator(Validator<T> validator) {
	}

}
