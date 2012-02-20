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

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Tree;
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
        Tree tree = new Tree();
        fillTreeComponent(tree, createTestNodes(), null);
        window.addComponent(tree);
    }
    
    private void fillTreeComponent(Tree tree, TreeNode node, Object parentId) {
        tree.addItem(node.getId());
        tree.setItemCaption(node.getId(), node.getName());
        boolean hasChildren = !node.getChildNodes().isEmpty();
        tree.setChildrenAllowed(node.getId(), hasChildren);
        if (parentId != null) {
            tree.setParent(node.getId(), parentId);
        }
        if (hasChildren) {
            for (TreeNode chNode : node.getChildNodes()) {
                fillTreeComponent(tree, chNode, node.getId());
            }
        }
    }
    
    private TreeNode createTestNodes() {
        TreeNode root = new TreeNode(-1L, "Disks");
        root.addChildNode(1L, "Disk 1").addChildNode(100L, "Films 1");
        root.addChildNode(2L, "Disk 2").addChildNode(200L, "Films 2");
        root.addChildNode(3L, "Disk 3").addChildNode(300L, "Films 3");
        root.addChildNode(4L, "Disk 4").addChildNode(400L, "Games 1");
        return root;
    }
}
