/* 
 * Copyright 2009 IT Mill Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.kelt.disk.db.web;

import java.util.Locale;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.DateField;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Select;
import com.vaadin.ui.Window;

/**
 * Demonstration application that shows how to use a simple custom client-side
 * GWT component, the ColorPicker.
 */
@SuppressWarnings("serial")
public class DiskDBApplication extends com.vaadin.Application {
    @Override
    public void init() {
        final Window mainWindow = new Window("Samplevaadin Application");
        final Window window = new Window("Some Window");
        window.setWidth(800, Sizeable.UNITS_PIXELS);
        window.setHeight(601, Sizeable.UNITS_PIXELS);
        window.setClosable(false);
        window.setDraggable(false);
        window.setResizable(false);
        window.center();
        setUpWindow(window);
        mainWindow.addWindow(window);
        setMainWindow(mainWindow);
    }

    private void setUpWindow(Window window) {
        // The locale in which we want to have the language
        // selection list
        Locale displayLocale = Locale.ENGLISH;

        // All known locales
        final Locale[] locales = Locale.getAvailableLocales();
        // Allow selecting a language. We are in a constructor of a
        // CustomComponent, so preselecting the current
        // language of the application can not be done before
        // this (and the selection) component are attached to
        // the application.
        final Select select = new Select("Select a language") {
            @Override
            public void attach() {
                setValue(getLocale());
            }
        };
        for (int i = 0; i < locales.length; i++) {
            select.addItem(locales[i]);
            select.setItemCaption(locales[i],
                    locales[i].getDisplayName(displayLocale));

            // Automatically select the current locale
            if (locales[i].equals(getLocale()))
                select.setValue(locales[i]);
        }
        window.addComponent(select);

        // Locale code of the selected locale
        final Label localeCode = new Label("");
        window.addComponent(localeCode);

        // A date field which language the selection will change
        final InlineDateField date = new InlineDateField(
                "Calendar in the selected language");
        date.setResolution(DateField.RESOLUTION_DAY);
        window.addComponent(date);

        // Handle language selection
        select.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                Locale locale = (Locale) select.getValue();
                date.setLocale(locale);
                localeCode.setValue("Locale code: " + locale.getLanguage()
                        + "_" + locale.getCountry());
            }
        });
        select.setImmediate(true);
    }
}
